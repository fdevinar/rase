import { formatDate } from "../utils/utils";

export default function RunDetails({ run }) {
    
    // console.log(run);        
    const report = run?.resultJson; 
    console.log(report);

    return (

        <div className="details-wrapper">            
            <div className="header-row">
                <div className="header-item">ID</div>
                <div className="header-item">Status</div>
                <div className="header-item">Created At</div>
            </div>

            {run && (
            <>
                <div className="detail-item">
                <p>{run.id}</p>
                <p>{run.status}</p>
                <p>{formatDate(run.createdAt)}</p>
                </div>

                <div className="results">
                <p>{run.resultJson}</p>
                </div>
            </>
            )}        
                                                              
        </div>

    )
}