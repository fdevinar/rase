import { useEffect, useState } from 'react'
import logo from './assets/rase-logo.png'
import './App.css'

function App() {
  const [runs, setRuns] = useState([]);

  useEffect(() => {
    fetchRuns();
  },[])

  async function fetchRuns() {
    const response = await fetch("http://localhost:8080/runs");
    const data = await response.json();
    console.log(data);
    setRuns(data);
  }

  return (
    <>
      <main>

        <div className="hero">
          <img src={logo}alt="logo" />          
        </div>                

      </main>                  
    </>
  )
}

export default App
