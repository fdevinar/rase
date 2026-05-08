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

  const jsonRequest = 
  {"scheduleId": "schedule-1","shifts": [{"shiftId": "shift-1","workerIds": ["worker-1", "worker-2"]},{"shiftId": "shift-2","workerIds": ["worker-3"]}]}

  async function runSchedule() {    
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(jsonRequest)
      };
    const response = await fetch('http://localhost:8080/schedule/execute',requestOptions);
    const data = await response.json();
    console.log(data);
    fetchRuns();
  }
  

  return (
    <>
      <main>

        <div className="hero">
          <img src={logo}alt="logo" />          
          <h1>Resource Allocation Simulation Engine</h1>
        </div>

        <button onClick={() => runSchedule()}>Perform a pre-made RUN</button>

        <h2>RUNS</h2>     
        <div className="runs-wrapper">
          {runs.map((run) => (<p>ID: {run.id} - STATUS: {run.status}</p>))}                            
        </div>   

      </main>                  
    </>
  )
}

export default App
