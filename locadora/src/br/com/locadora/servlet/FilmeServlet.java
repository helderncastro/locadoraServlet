package br.com.locadora.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.locadora.controller.FilmeController;
import br.com.locadora.model.Filme;

public class FilmeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			String filmesString = "";

			Gson gson = new Gson();

			FilmeController controller = new FilmeController();
			String idFilme = request.getParameter("idFilme");

			if (idFilme != null) {

				Integer id = new Integer(idFilme);
				Filme filme = controller.listarFilmeId(id);
				filmesString = gson.toJson(filme);
			} else {
				List<Filme> filmes = controller.listarFilmes();
				filmesString = gson.toJson(filmes);
			}

			out.print(filmesString);
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Filme filme = new Gson().fromJson(getBody(request), Filme.class);

			new FilmeController().cadastrarFilme(filme);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer idFilme = new Integer(request.getParameter("idFilme"));

			new FilmeController().deletarFilme(idFilme);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Filme filme = new Gson().fromJson(getBody(request), Filme.class);

			new FilmeController().atualizarFilme(filme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}

}
