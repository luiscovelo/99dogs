package br.fai.dogs.model.entities;

import java.util.Arrays;

public class Pessoa extends BasePojo {

	private String nome;
	private String telefone;
	private String email;
	private String senha;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private byte[] foto;
	private int numero;
	private String tipo;
	private String base64Foto;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getBase64Foto() {
		return base64Foto;
	}

	public void setBase64Foto(String base64Foto) {
		this.base64Foto = base64Foto;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha + ", rua="
				+ rua + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais
				+ ", foto=" + Arrays.toString(foto) + ", numero=" + numero + ", tipo=" + tipo + ", base64Foto="
				+ base64Foto + "]";
	}

}
