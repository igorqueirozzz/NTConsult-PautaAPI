
CREATE TABLE IF NOT EXISTS  nt_sessao (
	id_sessao BIGSERIAL PRIMARY KEY,
	pauta_id BIGINT,
	duracao BIGINT,
	final_sessao Time,
	status_sessao VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS  nt_pautas (
	id_pauta BIGSERIAL PRIMARY KEY,
	assunto VARCHAR(50) NOT NULL,
	descricao VARCHAR(100) NOT NULL,
	status VARCHAR NOT NULL,
	votos_sim INTEGER,
	votos_nao INTEGER,
	id_sessao BIGSERIAL REFERENCES nt_sessao(id_sessao)
);

CREATE TABLE IF NOT EXISTS  nt_votacoes_realizadas (
	id_votacoes_realizadas BIGSERIAL PRIMARY KEY,
	id_pauta BIGINT NOT NULL,
	cpf_cooperado VARCHAR NOT NULL
);