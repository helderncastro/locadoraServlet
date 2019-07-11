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

import br.com.locadora.controller.ClienteController;
import br.com.locadora.model.Cliente;

public class ClienteServlet extends HttpServlet {
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

			String clientesString = "";

			Gson gson = new Gson();

			ClienteController controller = new ClienteController();
			String idCliente = request.getParameter("idCliente");

			if (idCliente != null) {

				Integer id = new Integer(idCliente);
				Cliente cliente = controller.listarClienteId(id);
				clientesString = gson.toJson(cliente);
			} else {
				List<Cliente> clientes = controller.listarClientes();
				clientesString = gson.toJson(clientes);
			}

			out.print(clientesString);
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Cliente cliente = new Gson().fromJson(getBody(request), Cliente.class);

			if(cliente.getId() == null ) {
				new ClienteController().cadastrarCliente(cliente);				
			}else {
				new ClienteController().atualizarCliente(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer idCliente = new Integer(request.getParameter("idCliente"));

			new ClienteController().deletarCliente(idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Cliente cliente = new Gson().fromJson(getBody(request), Cliente.class);

			new ClienteController().atualizarCliente(cliente);
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
