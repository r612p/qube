# QUBE

QUBE is an interactive quantum computing learning platform designed to help students, educators, and enthusiasts explore the fundamentals of quantum mechanics and quantum computing through simulation. It provides both a dashboard for qubit management and a drag-and-drop circuit builder for experimenting with quantum gates and algorithms. Visit our website to get started: [r612p.github.io/qube](r612p.github.io/qube).


---

## Features

### Qubit Simulator
- **Create Qubits**: Define a qubit with custom amplitudes (a, b) for the |0⟩ and |1⟩ states.
- **Manage Qubits**: View, delete, or "uncollapse" qubits after measurement.
- **Logging**: Keep track of qubit operations and measurement history.

### Quantum Circuit Builder
- **Drag-and-Drop Interface**: Construct circuits visually using a variety of quantum gates.
- **Gate Support**: Includes common gates like X, Y, Z, H, S, T, and CNOT, with more advanced gates being added.
- **Execution**: Apply circuits to qubits and view results.

### Backend
- **Spring Boot API**: Handles qubit storage, gate execution, and measurement logic.
- **In-Memory Storage**: Qubits managed in a thread-safe `ConcurrentHashMap`.
- **Extensible Design**: Ready for integration with Firebase and future database support.

### Frontend
- **Dashboard**: Displays all qubits with controls for measurement, uncollapse, and deletion.
- **Circuit Workspace**: Drag, drop, and connect gates to qubits to simulate circuits.
- **Interactive Feedback**: Real-time updates as qubits are created, manipulated, and measured.

---

## Goals
- Provide an accessible way to **learn and experiment** with quantum concepts without needing a real quantum computer.
- Serve as a teaching aid for classrooms, clubs, and outreach events.
- Build a foundation for implementing advanced algorithms like Shor’s and Grover’s in a visual, educational format.

---

## Future Roadmap
- **Algorithm Modules**: Shor’s, Grover’s, Quantum Fourier Transform.
- **Persistence**: Store qubits and circuits with Firebase integration.
- **Collaboration**: Multi-user support for classrooms or team projects.
- **Visualization**: State vector and Bloch sphere displays.

---

