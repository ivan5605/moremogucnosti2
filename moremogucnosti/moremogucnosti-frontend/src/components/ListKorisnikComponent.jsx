import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { listKorisnici, deleteKorisnik } from '../services/KorisnikService';

const ListKorisnikComponent = () => {

  const navigator = useNavigate(); // Koristi se za navigaciju između stranica

  const [korisnici, setKorisnik] = useState([]) // Inicijalizacija stanja za korisnike 

  useEffect(() => {
    getAllKorisnici(); // Poziva funkciju za dohvat svih korisnika
  }, [])

  function getAllKorisnici() {
    listKorisnici().then(response => {
      setKorisnik(response.data) // Postavlja podatke korisnika u stanje komponente
    }).catch(error => {
      console.error("Greška kod dohvacanja korisnika!", error);
    });
  }

  function obrisiKorisnik(id) {
    console.log("Brisanje korisnika s ID-jem:", id);
    deleteKorisnik(id).then(response => {
      console.log("Korisnik uspješno obrisan!", response.data); // Ispisuje uspješnu poruku u konzolu
      getAllKorisnici(); // Navigira natrag na stranicu s korisnicima
    }).catch(error => {
      console.error("Greška kod brisanja korisnika!", error); // Ispisuje grešku u konzolu
    })
  }

  function updateKorisnik(id) {
    navigator(`/korisnici/uredi/${id}`)
  }

  return (
    <div className='container'>
      <h1 className='text-center'>Lista korisnika</h1>
      <button type='button' className='btn btn-outline-primary mb-2' onClick={() => navigator('/korisnici/uredi')}>Dodaj korisnika</button>
      <table className='table table-hover table-bordered'>
        <thead>
          <tr>
            <th>Id</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Email</th>
            <th>Lozinka</th>
          </tr>
        </thead>
        <tbody>
          {
            korisnici.map(korisnik =>
              <tr key={korisnik.id}>
                <td>{korisnik.id}</td>
                <td>{korisnik.ime}</td>
                <td>{korisnik.prezime}</td>
                <td>{korisnik.email}</td>
                <td>{korisnik.lozinka}</td>
                <td>
                  <button type='button' className='btn btn-outline-danger m-1 mb-0' onClick={() => obrisiKorisnik(korisnik.id)}>Obriši</button>
                  <button type='button' className='btn btn-outline-success' onClick={() => updateKorisnik(korisnik.id)}>Uredi</button>
                </td>
              </tr>
            )
          }
        </tbody>
      </table>
      <button className='btn btn-outline-danger' onClick={() => navigator('/')}>Natrag</button>
    </div>
  )
}

export default ListKorisnikComponent