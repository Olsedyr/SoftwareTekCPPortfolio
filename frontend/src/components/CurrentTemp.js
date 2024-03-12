import React, { useState, useEffect } from 'react';
import axios from 'axios';

const CurrentTemp = () => {
    const [odenseTemp, setOdenseTemp] = useState(null);
    const [cphTemp, setCphTemp] = useState(null);
    const [cphTempDate, setCphTempDate] = useState(null);
    const [odenseTempDate, setOdenseTempDate] = useState(null);

    const fetchData = async () => {
        try {
            // Fetch temperature data from backend API
            const responseOdense = await axios.get("http://localhost:8080/api/weather/odense/latest");
            const responseKøbenhavn = await axios.get("http://localhost:8080/api/weather/københavn/latest");

            // Extract temperature values from response data
            const { temperature_2m : odenseTemperature } = responseOdense.data;
            const { temperature_2m : cphTemperature } = responseKøbenhavn.data;

            const { time : odenseTemperatureDate } = responseOdense.data;
            const { time : cphTemperatureDate } = responseKøbenhavn.data;

            // Update state with temperature values
            setOdenseTemp(odenseTemperature);
            setCphTemp(cphTemperature);

            setCphTempDate(cphTemperatureDate)
            setOdenseTempDate(odenseTemperatureDate)
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
            <h2>Temperature for Odense and Copenhagen</h2>
            <div className="box-content">
                <br/>
                <p>
                    <strong>OdenseTemp:  </strong>
                    {odenseTemp !== null ? `${odenseTemp} °C` : ''}
                    <br/>
                    {odenseTempDate !== null ? `${new Date(odenseTempDate).toLocaleString('en-DK',{ hour12: false })} ` : ''}
                </p>
                <br/>
                <p>
                    <strong>CphTemp:  </strong>
                    {cphTemp !== null ? `${cphTemp} °C` : ''}
                    <br/>
                    {cphTempDate !== null ? `${new Date(cphTempDate).toLocaleString('en-DK', { hour12: false })} ` : ''}
                </p>
            </div>
        </div>
    );
};

export default CurrentTemp;
