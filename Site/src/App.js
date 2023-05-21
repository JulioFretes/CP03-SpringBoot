import React, { useState, useEffect } from 'react';

const Cachorro = () => {
  const [cachorros, setCachorros] = useState([]);
  const [codigo, setCodigo] = useState('');
  const [nome, setNome] = useState('');
  const [raca, setRaca] = useState('');
  const [sexo, setSexo] = useState('');

  useEffect(() => {
    fetchCachorros();
  }, []);

  const fetchCachorros = async () => {
    try {
      const response = await fetch('http://localhost:8080/cachorro');
      const data = await response.json();
      setCachorros(data);
    } catch (error) {
      console.error(error);
    }
  };

  const fetchCachorro = async (codigo) => {
    try {
      const response = await fetch(`http://localhost:8080/cachorro/${codigo}`);
      const data = await response.json();
      setCodigo(data.codigo);
      setNome(data.nome);
      setRaca(data.raca);
      setSexo(data.sexo);
    } catch (error) {
      console.error(error);
    }
  };

  const createCachorro = async () => {
    try {
      const response = await fetch('http://localhost:8080/cachorro', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          nome,
          raca,
          sexo,
        }),
      });
      if (response.ok) {
        fetchCachorros();
        setNome('');
        setRaca('');
        setSexo('');
      } else {
        console.error('Erro ao criar cachorro');
      }
    } catch (error) {
      console.error(error);
    }
  };

  const updateCachorro = async () => {
    try {
      const response = await fetch(`http://localhost:8080/cachorro/${codigo}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          codigo,
          nome,
          raca,
          sexo,
        }),
      });
      if (response.ok) {
        fetchCachorros();
        setCodigo('');
        setNome('');
        setRaca('');
        setSexo('');
      } else {
        console.error('Erro ao atualizar cachorro');
      }
    } catch (error) {
      console.error(error);
    }
  };

  const deleteCachorro = async (codigo) => {
    try {
      const response = await fetch(`http://localhost:8080/cachorro/${codigo}`, {
        method: 'DELETE',
      });
      if (response.ok) {
        fetchCachorros();
      } else {
        console.error('Erro ao excluir cachorro');
      }
    } catch (error) {
      console.error(error);
    }
  };

  const clearFields = () => {
    setCodigo('');
    setNome('');
    setRaca('');
    setSexo('');
  };

  return (
    <div>
      <h1>Lista de Cachorros</h1>
      <ul>
        {cachorros.map((cachorro) => (
          <li key={cachorro.codigo}>
            {cachorro.nome} - {cachorro.raca} - {cachorro.sexo}
            <button onClick={() => fetchCachorro(cachorro.codigo)}>Detalhes</button>
            <button onClick={() => {
              setCodigo(cachorro.codigo);
              setNome(cachorro.nome);
              setRaca(cachorro.raca);
              setSexo(cachorro.sexo);
            }}>Editar</button>
            <button onClick={() => deleteCachorro(cachorro.codigo)}>Excluir</button>
          </li>
        ))}
      </ul>
      <h2>{codigo ? 'Editar Cachorro' : 'Criar Cachorro'}</h2>
      <form onSubmit={(e) => {
        e.preventDefault();
        if (codigo) {
          updateCachorro();
        } else {
          createCachorro();
        }
        clearFields();
      }}>
        <label>
          Nome:
          <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} />
        </label>
        <label>
          Raça:
          <input type="text" value={raca} onChange={(e) => setRaca(e.target.value)} />
        </label>
        <label>
          Sexo:
          <input type="text" value={sexo} onChange={(e) => setSexo(e.target.value)} />
        </label>
        <button type="submit">{codigo ? 'Atualizar' : 'Criar'}</button>
      </form>
    </div>
  );
};

export default Cachorro;
