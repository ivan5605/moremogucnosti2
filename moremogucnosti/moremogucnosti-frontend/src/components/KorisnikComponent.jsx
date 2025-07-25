import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { addKorisnik, getKorisnik, updateKorisnik } from '../services/KorisnikService'

const KorisnikComponent = () => {

  const [ime, setIme] = useState('') // Inicijalizacija stanja za ime korisnika
  const [prezime, setPrezime] = useState('')
  const [email, setEmail] = useState('')
  const [lozinka, setLozinka] = useState('')

  // moze i kao jedan objekt korisnik
  // const [korisnik, setKorisnik] = useState({
  //   ime: '', 
  //   prezime: '',
  //   email: '',
  //   lozinka: ''
  // })
  // setKorisnik({...korisnik, ime: dogadaj.target.value}) // Ažurira samo ime korisnika


  const { id } = useParams();

  const navigator = useNavigate();

  const [errors, setErrors] = useState({
    ime: '',
    prezime: '',
    email: '',
    lozinka: ''
  })

  const [emailError, setEmailError] = useState('');

  useEffect(() => {
    getKorisnik(id).then((response) => {
      setIme(response.data.ime);
      setPrezime(response.data.prezime);
      setEmail(response.data.email);
      setLozinka(response.data.lozinka);
    }).catch(error => {
      console.error(error.response.data);
    })
  }, [id])

  function provjeriUnos() { //razdvoji u funkcije, validacija emaila, trim unapred za sve inpute
    let provjera = true;

    const errorsCopy = { ...errors } // Kopira postojeće greške u novi objekt

    if (ime.trim()) {
      errorsCopy.ime = '';
    } else {
      errorsCopy.ime = "Ime korisnika obavezno je unijeti!"
      provjera = false;
    }

    if (prezime.trim()) {
      errorsCopy.prezime = '';
    } else {
      errorsCopy.prezime = "Prezime korisnika obavezno je unijeti!"
      provjera = false;
    }

    if (email.trim()) {
      errorsCopy.email = '';
    } else {
      errorsCopy.email = "Email korisnika obavezno je unijeti!"
      provjera = false;
    }

    if (!lozinka.trim()) {
      errorsCopy.lozinka = "Lozinka korisnika obavezno je unijeti!"
      provjera = false;
    } else if (lozinka.length < 5) {
      errorsCopy.lozinka = "Lozinka mora imati najmanje 5 znakova!"
      provjera = false;
    } else if (!/[A-Z]/.test(lozinka)) {
      errorsCopy.lozinka = "Lozinka mora imati barem jedno veliko slovo!"
      provjera = false;
    } else {
      errorsCopy.lozinka = "";
    }

    setErrors(errorsCopy); // Ažurira stanje grešaka
    return provjera; // Vraća rezultat provjere
  }

  function spremiIliUpdateKorisnik(dogadaj) {
    dogadaj.preventDefault(); // Sprječava ponovno učitavanje stranice

    if (provjeriUnos()) {
      const korisnik = { ime, prezime, email, lozinka }; // Stvara objekt korisnika s podacima iz stanja
      console.log('Korisnik podaci:', korisnik); // Ispisuje podatke korisnika u konzolu

      if (id) {
        updateKorisnik(id, korisnik).then((response) => {
          console.log("Korisnik ažuriran!");
          navigator('/korisnici');
        }).catch(error => {
          setEmailError(error.response.data.message);

        })
      } else {
        addKorisnik(korisnik).then(response => {
          console.log("Korisnik uspješno dodan!", response.data); // Ispisuje uspješnu poruku u konzolu
          navigator('/korisnici'); // Navigira natrag na stranicu s korisnicima
        }).catch(error => {
          if (error.response && error.response.data && error.response.data.message) {
            setEmailError(error.response.data.message);
          } else {
            setEmailError("Došlo je do greške!")
          }
        })
      }
    }
  }



  return (
    <div className='container mt-3'>
      <div className='row'>
        <div className='col-md-6 offset-md-3'>
          <h3 className='text-center'>Dodaj novog korisnika</h3>
          <div className='card-body'>

            <form>
              <div className='form-group mb-2'>
                <label>Ime:</label>
                <input
                  type="text"
                  className={`form-control ${errors.ime ? 'is-invalid' : ''}`}
                  placeholder='Unesite ime korisnika'
                  name='ime'
                  value={ime}
                  onChange={(dogadaj) => setIme(dogadaj.target.value)} ></input>
                {errors.ime && <div className='invalid-feedback'>{errors.ime}</div>}
              </div>

              <div className='form-group mb-2'>
                <label>Prezime:</label>
                <input
                  type="text"
                  className={`form-control ${errors.prezime ? 'is-invalid' : ''}`} // Dinamički dodaje klasu za validaciju
                  placeholder='Unesite prezime korisnika'
                  name='prezime'
                  value={prezime}
                  onChange={(dogadaj) => setPrezime(dogadaj.target.value)} ></input>
                {errors.prezime && <div className='invalid-feedback'>{errors.prezime}</div>}
              </div>

              <div className='form-group mb-2'>
                <label>Email:</label>
                <input
                  type="text"
                  className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                  placeholder='Unesite email korisnika'
                  name='email'
                  value={email}
                  onChange={(dogadaj) => setEmail(dogadaj.target.value)} ></input>
                {errors.email && <div className='invalid-feedback'>{errors.email}</div>}
              </div>

              <div className='form-group mb-2'>
                <label>Lozinka:</label>
                <input
                  type="text"
                  className={`form-control ${errors.lozinka ? 'is-invalid' : ''}`}
                  placeholder='Unesite lozinku korisnika'
                  name='lozinka'
                  value={lozinka}
                  onChange={(dogadaj) => setLozinka(dogadaj.target.value)} ></input>
                {errors.lozinka && <div className='invalid-feedback'>{errors.lozinka}</div>}
              </div>
              {emailError && <div className="alert alert-danger mt-3">{emailError}</div>}
              <button type='button' className='btn btn-outline-primary' onClick={spremiIliUpdateKorisnik}>Potvrdi</button>
              <button type='button' className='btn btn-outline-danger m-2' onClick={() => navigator('/korisnici')}>Natrag</button>


            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default KorisnikComponent