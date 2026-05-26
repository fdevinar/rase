import { formatDate } from "../utils/utils";

export default function RunList({ runs, setSelectedRun }) {
    
    const sortedRuns = [...runs].sort((a, b) => b.id - a.id);
    
    return (
        <div className="runs-wrapper">
            <div className="header-row">
                <div className="header-item">ID</div>
                <div className="header-item">Status</div>
                <div className="header-item">Created At</div>
            </div>
            {sortedRuns.map((run) =>                     
                    <button
                        className={`run-item ${run.status === 'SUCCESS' ? 'green' : 'red'}`}
                                             
                        onClick={()=>setSelectedRun(run.id)}
                     >
                        <p>{run.id}</p>
                        <p>{run.status}</p>
                        <p>{formatDate(run.createdAt)}</p>
                    </button>
                )}                      
        </div>   
    )

}