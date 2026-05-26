export function formatDate(date) {
    return new Date().toISOString().split('T')[0];
}

export function generateScenario(type = "NORMAL") {

  const workers = [
    "worker-1",
    "worker-2",
    "worker-3",
    "worker-4",
    "worker-5"
  ];

  let totalShifts = 5;
  let maxWorkersPerShift = 2;

  switch(type) {

    case "OVERWORKED":
      totalShifts = 18;
      maxWorkersPerShift = 1;
      break;

    case "CHAOTIC":
      totalShifts = randomBetween(8, 20);
      maxWorkersPerShift = 4;
      break;

    case "UNDERSTAFFED":
      totalShifts = 12;
      maxWorkersPerShift = 1;
      break;

    case "FATIGUE_HELL":
      totalShifts = 25;
      maxWorkersPerShift = 1;
      break;

    case "DUPLICATE_ASSIGNMENTS":
      totalShifts = 10;
      maxWorkersPerShift = 3;
      break;

    default:
      totalShifts = randomBetween(4, 10);
      maxWorkersPerShift = 2;
  }

  const shifts = [];

  for (let i = 1; i <= totalShifts; i++) {

    let selectedWorkers = [];

    switch(type) {

      case "OVERWORKED":
      case "FATIGUE_HELL":

        // Same worker repeatedly
        selectedWorkers = ["worker-1"];
        break;

      case "UNDERSTAFFED":

        // Occasionally empty shifts
        if (Math.random() < 0.3) {
          selectedWorkers = [];
        } else {
          selectedWorkers = [randomItem(workers)];
        }
        break;

      case "DUPLICATE_ASSIGNMENTS":

        // Intentionally duplicate workers
        const duplicatedWorker = randomItem(workers);

        selectedWorkers = [
          duplicatedWorker,
          duplicatedWorker
        ];
        break;

      case "CHAOTIC":

        // Pure madness
        const totalWorkers = randomBetween(0, maxWorkersPerShift);

        for (let j = 0; j < totalWorkers; j++) {
          selectedWorkers.push(randomItem(workers));
        }

        break;

      default:

        const workerCount = randomBetween(1, maxWorkersPerShift);

        selectedWorkers = shuffle(workers)
          .slice(0, workerCount);
    }

    shifts.push({
      shiftId: `shift-${i}`,
      workerIds: selectedWorkers
    });
  }

  return {
    scheduleId: `schedule-${type}-${Date.now()}`,
    shifts
  };
}

function randomBetween(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function randomItem(array) {
  return array[Math.floor(Math.random() * array.length)];
}

function shuffle(array) {
  return [...array].sort(() => Math.random() - 0.5);
}



// DEFAULT

// export function generateRandomSchedule() {
//   const workers = [
//     "worker-1",
//     "worker-2",
//     "worker-3",
//     "worker-4",
//     "worker-5"
//   ];

//   const totalShifts = randomBetween(3, 12);

//   const shifts = [];

//   for (let i = 1; i <= totalShifts; i++) {

//     const workersInShift = randomBetween(1, 3);

//     const selectedWorkers = shuffle(workers)
//       .slice(0, workersInShift);

//     shifts.push({
//       shiftId: `shift-${i}`,
//       workerIds: selectedWorkers
//     });
//   }

//   return {
//     scheduleId: `schedule-${Date.now()}`,
//     shifts
//   };
// }

// function randomBetween(min, max) {
//   return Math.floor(Math.random() * (max - min + 1)) + min;
// }

// function shuffle(array) {
//   return [...array].sort(() => Math.random() - 0.5);
// }