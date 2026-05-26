import { useEffect, useState } from 'react'
import logo from './assets/rase-logo.png'
import './App.css'
import RunList from './components/RunList';
import RunDetails from './components/RunDetails';
import { generateScenario } from './utils/utils';

function App() {
  const [runs, setRuns] = useState([]);
  const [customReq, setCustomReq] = useState("");
  const [selectedRun, setSelectedRun] = useState(null);
  // const jsonRequest = 
  // {"scheduleId": "schedule-1","shifts": [{"shiftId": "shift-1","workerIds": ["worker-1", "worker-2"]},{"shiftId": "shift-2","workerIds": ["worker-3"]}]}
  // const jsonRequest = generateRandomSchedule();
  const [randomRequest, setRandomRequest]= useState();

  useEffect(() => {
    fetchRuns();
  },[])

  async function fetchRuns() {
    const response = await fetch("http://localhost:8080/runs");
    const data = await response.json();
    // console.log("Fetched Runs:");
    // console.log(data);
    setRuns(data);
  }

  function handleSubmit(event) {
    event.preventDefault();
    try {
      const parsedRequest = JSON.parse(customReq);
      runSchedule(parsedRequest);
    }
    catch {
      alert("Invalid JSON Request");
    }    
  }

  function generateRandomSchedule(type = "NORMAL") {    
    setRandomRequest(generateScenario(type));    
    runSchedule(randomRequest);
  }

  async function runSchedule(schedule = randomRequest) {    
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(schedule)
      };
    const response = await fetch('http://localhost:8080/schedule/execute',requestOptions);
    const data = await response.json();
    // console.log(data);
    fetchRuns();
  }
  
  function onSelectedRun(id) {
    const selectedRun = runs.find(run => run.id === id);
    // console.log("Selected Run:");
    // console.log(selectedRun);
    setSelectedRun(selectedRun);    
  }

  return (
    <>
      <main>

        <div className="hero">
          <img src={logo}alt="logo" />          
          <h1>Resource Allocation Simulation Engine</h1>
        </div>
        
        <div className="main-content clear-box">

          <div className="sidebar">
            <h2>Simulation Runs</h2>     
            <RunList
              runs={runs}
              setSelectedRun={(id) => onSelectedRun(id)}
            >
            </RunList>
          </div>
                    
          <RunDetails
            run={selectedRun}              
          ></RunDetails>          

        </div>

        <div className="random-bar clear-box">
          <button onClick={() => generateRandomSchedule()}>Send NORMAL Request</button>
          <button onClick={() => generateRandomSchedule("OVERWORKED")}>Send OVERWORKED Request</button>
          <button onClick={() => generateRandomSchedule("CHAOTIC")}>Send CHAOTIC Request</button>
          <button onClick={() => generateRandomSchedule("UNDERSTAFFED")}>Send UNDERSTAFFED Request</button>
          <button onClick={() => generateRandomSchedule("FATIGUE_HELL")}>Send FATIGUE_HELL Request</button>
          <button onClick={() => generateRandomSchedule("DUPLICATE_ASSIGNMENTS")}>Send DUPLICATE_ASSIGNMENTS Request</button>
        </div>

        <div className="execution-bar clear-box">

                      
          <form onSubmit={handleSubmit}>
            <label>Send custom request: </label>
            <textarea 
              type="text" 
              value={customReq}
              onChange={(e)=> setCustomReq(e.target.value)}
            />
            <input type="submit" />
          </form>

        </div>

      </main>                  
    </>
  )
}

export default App
