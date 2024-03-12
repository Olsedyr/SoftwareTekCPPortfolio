import React, { useState, useEffect, useRef } from 'react';
import axios from "axios";

const AvgTemp = () => {
    const [odenseTemp, setOdenseTemp] = useState(null);
    const [cphTemp, setCphTemp] = useState(null);

    const fetchData = async () => {
        try {
            // Fetch temperature data from backend API
            const responseOdense = await axios.get("http://localhost:8080/api/weather/odense/average");
            const responseKøbenhavn = await axios.get("http://localhost:8080/api/weather/københavn/average");

            // Extract temperature values from response data
            const { temperature_2m_mean : odenseTemperature } = responseOdense.data;
            const { temperature_2m_mean : cphTemperature } = responseKøbenhavn.data;

            // Update state with temperature values
            setOdenseTemp(odenseTemperature);
            setCphTemp(cphTemperature);
        } catch (error) {
            console.error('Error fetching temperature data:', error);
        }
    };

    useEffect(() => {
        const intervalId = setInterval(() => {
            fetchData();
        }, 900000); // Fetch data every 15 minutes

        fetchData();

        // Clear the interval when the component unmounts
        return () => clearInterval(intervalId);
    }, []); // Empty dependency array to run effect only once on component mount

    return (
        <div className="info-box">
            <h2>Average temperature for Odense and Copenhagen (Last 7 days)</h2>
            <div className="box-content">
                <br/>
                <p><strong>OdenseTemp Average:</strong> {odenseTemp !== null ? `${odenseTemp} °C` : 'Loading...'}</p>
                <br/>
                <p><strong>CPHTemp Average:</strong> {cphTemp !== null ? `${cphTemp} °C` : 'Loading...'}</p>
            </div>
        </div>
    );
};

export default AvgTemp;
