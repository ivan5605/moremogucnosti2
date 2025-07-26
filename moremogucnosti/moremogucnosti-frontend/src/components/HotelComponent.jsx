import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { createHotel, updateHotel, getHotel } from '../services/HotelService'

//github copliot molim te da mi predlažeš samo komentare na hrvatskom jeziku, nemoj da mi predlažeš kod

const HotelComponent = () => {

  const [naziv, setNaziv] = useState('') // Inicijalizacija stanja za naziv hotela
  const [lokacija, setLokacija] = useState('') // Inicijalizacija stanja za lokaciju hotela
  const [cijenaNocenja, setCijenaNocenja] = useState('') // Inicijalizacija stanja za cijenu noćenja

  const navigator = useNavigate(); // Koristi se za navigaciju između stranica

  const { id } = useParams();
  //const params = useParams();
  //const id = params.id;

  //nemoj mi predlagati kod samo komentare na hrvatskom jeziku
  const [errors, setErrors] = useState({ //useState je funkcija koja omogućava da u funkcionalnoj komponenti pamrtimo vrijednost (state) koja se može mijenjati
    naziv: '',
    lokacija: '',
    cijenaNocenja: ''
  })
  //errors je objekat sa 3 polja, setErrors je funkcija koja se koristi za ažuriranje stanja errors

  useEffect(() => {
    if (id) {
      getHotel(id).then((response) => {
        setNaziv(response.data.naziv);
        setLokacija(response.data.lokacija);
        setCijenaNocenja(response.data.cijenaNocenja);
      }).catch(error => {
        console.error(error);
      })
    }
  }, [id])

  function spremiIliUpdateHotel(dogadaj) {
    dogadaj.preventDefault(); // Sprječava ponovno učitavanje stranice


    if (provjeriUnos()) {
      const hotel = { naziv, lokacija, cijenaNocenja }; // Stvara objekt hotela s podacima iz stanja
      console.log('Hotel podaci:', hotel); // Ispisuje podatke hotela u konzolu

      if (id) {
        updateHotel(id, hotel).then((response) => {
          console.log(response.data);
          navigator('/hoteli')
        }).catch(error => {
          console.error(error);
        })
      } else {
        createHotel(hotel).then(response => {
          console.log("Hotel uspješno dodan!", response.data); // Ispisuje uspješnu poruku u konzolu
          navigator('/hoteli'); // Navigira natrag na stranicu s hotelima
        }).catch(error => {
          console.error("Greška kod dodavanja hotela!", error.response.data); // Ispisuje grešku u konzolu
        })
      }
    }
  }

  function provjeriUnos() {
    let provjera = true;

    const errorsCopy = { ...errors } // Kopira postojeće greške u novi objekt, da ne bi direktno mutirao stanje, nije referenca, zato se koristi ... (spread operator)

    if (naziv.trim()) {
      errorsCopy.naziv = '';
    } else {
      errorsCopy.naziv = "Naziv hotela obavezno je unijeti!"
      provjera = false;
    }

    if (lokacija.trim()) {
      errorsCopy.lokacija = '';
    } else {
      errorsCopy.lokacija = "Lokaciju hotela obavezno je unijeti!"
      provjera = false;
    }

    if (cijenaNocenja) {
      errorsCopy.cijenaNocenja = '';
    } else {
      errorsCopy.cijenaNocenja = "Cijenu noćenja za hotel obavezno je unijeti!"
      provjera = false;
    }

    setErrors(errorsCopy); // Ažurira stanje grešaka s novim vrijednostima
    return provjera;
  }

  return (
    <div className='container mt-3'>
      <div className='row'>
        <div className='col-md-6 offset-md-3'>
          <h3 className='text-center'>Dodaj novi hotel</h3>
          <div className='card-body'>
            <form>
              <div className='form-group mb-2'>
                <label>Naziv:</label>
                <input
                  type='text'
                  className={`form-control ${errors.naziv ? 'is-invalid' : ''}`} // Dinamički dodaje klasu za validaciju
                  placeholder='Unesite naziv hotela'
                  name='naziv'
                  value={naziv}
                  onChange={(dogadaj) => setNaziv(dogadaj.target.value)} ></input>
                {errors.naziv && <div className='invalid-feedback'>{errors.naziv}</div>}
                {/* A && B - vraća B ako je A "truthy" */}

              </div>

              <div className='form-group mb-2'>
                <label>Lokacija:</label>
                <input
                  type='text'
                  className={`form-control ${errors.lokacija ? 'is-invalid' : ''}`}
                  placeholder='Unesite lokaciju hotela'
                  name='lokacija'
                  value={lokacija}
                  onChange={(dogadaj) => setLokacija(dogadaj.target.value)} ></input>
                {errors.lokacija && <div className='invalid-feedback'>{errors.lokacija}</div>}
              </div>

              <div className='form-group mb-2'>
                <label>Cijena noćenja:</label>
                <input
                  type='number'
                  className={`form-control ${errors.cijenaNocenja ? 'is-invalid' : ''}`}
                  placeholder='Unesite cijenu noćenja'
                  name='cijenaNocenja'
                  value={cijenaNocenja}
                  onChange={(dogadaj) => setCijenaNocenja(parseInt(dogadaj.target.value))} ></input>
                {errors.cijenaNocenja && <div className='invalid-feedback'>{errors.cijenaNocenja}</div>}
              </div>

              <button type='button' className='btn btn-outline-primary' onClick={spremiIliUpdateHotel}>Potvrdi</button>
              <button type='button' className='btn btn-outline-danger m-2' onClick={() => navigator('/hoteli')}>Natrag</button>
            </form>

          </div>
        </div>
      </div>
    </div>
  )
}

export default HotelComponent