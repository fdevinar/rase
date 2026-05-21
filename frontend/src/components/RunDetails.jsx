import { formatDate } from "../utils/utils";

export default function RunDetails({ run }) {
    
    // console.log(run);
    const report = run?.resultJson;    
    let executionReport;
    let policyResults;
    let workerResults;
    let successRate;
    if (report) {
        const parsedReport = JSON.parse(report);        
        executionReport = parsedReport.executionReport;
        policyResults = parsedReport.policyResults;
        workerResults = parsedReport.workerResults;
        successRate = (executionReport.successfulShifts / executionReport.totalShifts) * 100 ;
        console.log("** Execution Report **");
        console.log(executionReport);
        console.log("** Policy Results **");
        console.log(policyResults);
        console.log("** Worker Results **");
        console.log(workerResults);
        console.log("** Success Rate **");
        console.log(successRate);
    }
        
    return (

        <div className="details-wrapper">   

            <h2>Run Details</h2>

            {/* BASICS */}
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

                {/* <div className="results">
                <p>{run.resultJson}</p>
                </div> */}
            </>
            )}     

            {/* EXECUTION REPORT */}

            {executionReport && (
            <>
                <h2>Shift Execution Overview</h2>
                <div className="execution-report">
                    <div className="execution-item total">
                        <p className="number">{executionReport.totalShifts}</p>
                        <p className="text">Total Shifts</p>
                    </div>
                    <div className="execution-item success">
                        <p className="number">{executionReport.successfulShifts}</p>
                        <p className="text">Successful</p>
                    </div>
                    <div className="execution-item fail">
                        <p className="number">{executionReport.failedShifts}</p>
                        <p className="text">Failed</p>
                    </div>
                    <div className="execution-item percentage">
                        <p className="number">{successRate.toFixed(0)} %</p>
                        <p className="text">Success Rate</p>
                    </div>
                </div>
            </>
            )

            }
                                                              
        </div>

    )
}