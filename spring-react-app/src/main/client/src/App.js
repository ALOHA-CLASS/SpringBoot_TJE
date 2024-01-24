import React, { useEffect, useState } from "react";
import './App.css';
import logo from './logo.svg';

function App() {
  const [message, setMessage] = useState("");

    useEffect(() => {
      fetch('/home')
          .then(response => response.text())
          .then(message => {
              setMessage(message);
          });
    },[])
    return (
      <div className="App">
          <header className="App-header">
              <img src={logo} className="App-logo" alt="logo"/>
              <h1 className="App-title">{message}</h1>
          </header>
      </div>
    )
}

export default App;
