package com.bancoImobiliario.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.bancoImobiliario.demo.model.Dado;
import com.bancoImobiliario.demo.model.Estatistica;
import com.bancoImobiliario.demo.model.Propriedade;
import com.bancoImobiliario.demo.model.jogador.Aleatorio;
import com.bancoImobiliario.demo.model.jogador.Cauteloso;
import com.bancoImobiliario.demo.model.jogador.Exigente;
import com.bancoImobiliario.demo.model.jogador.Impulsivo;
import com.bancoImobiliario.demo.model.jogador.Jogador;

@Service
public class IndexService {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private Environment env;

	public List<Propriedade> carregaTabuleiro() throws Exception {

		List<Propriedade> tabuleiro = new ArrayList<Propriedade>();

		// Monta o tabuleiro de acordo com o arquivo de configurações
		Resource resource = resourceLoader.getResource("classpath:static/gameConfig.txt");
		BufferedReader gameConfig = new BufferedReader(new InputStreamReader(resource.getInputStream()));

		while (true) {
			String line = gameConfig.readLine();
			if (line == null)
				break;
			tabuleiro.add(new Propriedade(Integer.parseInt(line.substring(0, line.indexOf(" "))),
					Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1, line.length())), null));
		}

		return tabuleiro;
	}

	public List<Jogador> carregaJogadores() {

		Jogador aleatorio = new Aleatorio();
		Jogador cauteloso = new Cauteloso();
		Jogador exigente = new Exigente();
		Jogador impulsivo = new Impulsivo();

		List<Jogador> jogadores = new ArrayList<Jogador>();
		jogadores.add(aleatorio);
		jogadores.add(cauteloso);
		jogadores.add(exigente);
		jogadores.add(impulsivo);

		return jogadores;

	}

	public Integer numeroDeJogadoresAtivos(List<Jogador> jogadores) {
		Integer ativos = 0;
		for (Jogador j : jogadores) {
			if (j.getAtivo())
				ativos++;
		}
		return ativos;
	}

	public Jogador defineGanhador(List<Jogador> jogadores) {
		int vencedor = 0;
		Integer valor = -1;
		for (int i = 0; i < jogadores.size(); i++) {
			if (jogadores.get(i).getCoins() > valor) {
				vencedor = i;
				valor = jogadores.get(i).getCoins();
			}
		}

		return (jogadores.get(vencedor));
	}

	public Estatistica rodarSimulacao(Integer partidas) throws Exception {
		Estatistica estatistica = new Estatistica();
		Integer totalRodadas = 0;

		// Carrega as propriedades utilizando o arquivo de texto
		List<Propriedade> tabuleiro = carregaTabuleiro();

		// Carrega os jogadores
		List<Jogador> jogadores = carregaJogadores();

		// Contador de rodadas
		Integer rodada;

		// Status da partida
		Boolean fimDeJogo;

		// Parametros simulacao
		Integer rodadas = Integer.parseInt(env.getProperty("simulacao.rodadas"));
		Integer coinsInicio = Integer.parseInt(env.getProperty("coins.inicio"));
		Integer coinsVoltaCompleta = Integer.parseInt(env.getProperty("coins.voltaCompleta"));

		for (Integer partida = 1; partida <= partidas; partida++) {

			// reseta partida
			rodada = 1;
			fimDeJogo = false;

			// reseta o tabuleiro
			tabuleiro.forEach(p -> p.setProprietario(null));
			// reseta os jogadores
			jogadores.forEach(j -> {
				j.setAtivo(true);
				j.setCoins(coinsInicio);
				j.setPosicao(null);
			});

			// Define uma ordem aleatória para os jogadores
			Collections.shuffle(jogadores);

			while (rodada <= rodadas && !fimDeJogo) {

				for (Jogador j : jogadores) {
					if (j.getAtivo()) {
						// Rola o dado e define a posicao do Jogador
						Integer jogada = Dado.rolar();

						if (j.getPosicao() == null) {
							j.setPosicao(jogada);
						} else if (j.getPosicao() + jogada > tabuleiro.size()) {
							j.setPosicao(j.getPosicao() + jogada - tabuleiro.size());
							j.setCoins(j.getCoins() + coinsVoltaCompleta);
						} else {
							j.setPosicao(j.getPosicao() + jogada);
						}

						Propriedade localAtual = tabuleiro.get(j.getPosicao() - 1);
						Jogador proprietario = localAtual.getProprietario();
						// Se o local está vazio, o jogador decide se compra ou não
						if (proprietario == null) {
							if (j.compra(localAtual) && j.getCoins() - localAtual.getPreco() > 0) {
								localAtual.setProprietario(j);
								j.setCoins(j.getCoins() - localAtual.getPreco());
							}
							// Se o local já está ocupado, paga o aluguel para o dono
						} else {
							// Se o dono é o próprio jogador, não faz nada
							if (!j.equals(proprietario)) {
								// Se o aluguel é maior ou igual ao saldo, desativa o jogador e zera o saldo
								if (localAtual.getAluguel() >= j.getCoins()) {
									proprietario.setCoins(proprietario.getCoins() + j.getCoins());
									j.setCoins(0);
									j.setAtivo(false);

									tabuleiro.forEach(p -> {
										if (j.equals(p.getProprietario())) {
											p.setProprietario(null);
										}
									});
									// Se o aluguel é menor que o saldo, faz o pagamento
								} else {
									proprietario.setCoins(proprietario.getCoins() + localAtual.getAluguel());
									j.setCoins(j.getCoins() - localAtual.getAluguel());
								}
							}
						}

						if (numeroDeJogadoresAtivos(jogadores) == 1) {
							fimDeJogo = true;
							break;
						}
					}

				}
				rodada++;
			}
			if (rodada > rodadas) {
				estatistica.setTimeout(estatistica.getTimeout() + 1);
			}
			totalRodadas += rodada - 1;
			Jogador vencedor = defineGanhador(jogadores);
			vencedor.setVitorias(vencedor.getVitorias() + 1);

			estatistica.setLogPartidas(estatistica.getLogPartidas() + "Vencedor " + partida.toString() + ": "
					+ vencedor.getClass().getSimpleName() + "<br>");
		}
		estatistica.setMediaTurnos(totalRodadas / Double.parseDouble(partidas.toString()));

		Integer maiorNumeroVitorias = 0;
		for (Jogador j : jogadores) {
			switch (j.getClass().getSimpleName()) {
			case "Aleatorio":
				estatistica.setPercAleatorio(j.getVitorias() / Double.parseDouble(partidas.toString()));
				break;
			case "Cauteloso":
				estatistica.setPercCauteloso(j.getVitorias() / Double.parseDouble(partidas.toString()));
				break;
			case "Exigente":
				estatistica.setPercExigente(j.getVitorias() / Double.parseDouble(partidas.toString()));
				break;
			case "Impulsivo":
				estatistica.setPercImpulsivo(j.getVitorias() / Double.parseDouble(partidas.toString()));
				break;
			}
			if (j.getVitorias() > maiorNumeroVitorias) {
				estatistica.setVencedor(j.getClass().getSimpleName());
				maiorNumeroVitorias = j.getVitorias();
			}
		}

		System.out.println("Partidas terminadas por time out: " + estatistica.getTimeout().toString());
		System.out.println("Media de turnos por partida: " + estatistica.getMediaTurnos().toString());
		System.out.println("Porcentagem de vitórias por comportamento dos jogadores");
		System.out.println(" - Aleatorio: " + (estatistica.getPercAleatorio() * 100) + "%");
		System.out.println(" - Cauteloso: " + (estatistica.getPercCauteloso() * 100) + "%");
		System.out.println(" - Exigente: " + (estatistica.getPercExigente() * 100) + "%");
		System.out.println(" - Impulsivo: " + (estatistica.getPercImpulsivo() * 100) + "%");
		System.out.println("Comportamento que mais vence: " + estatistica.getVencedor());

		return estatistica;
	}

}
