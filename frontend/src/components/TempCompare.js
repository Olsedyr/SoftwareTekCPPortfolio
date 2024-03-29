import React, { useState, useEffect, useRef } from 'react';
import axios from "axios";

const CurrentTemp = () => {

    const [odenseTemp, setOdenseTemp] = useState(null);
    const [cphTemp, setCphTemp] = useState(null);
    const [tempDifference, settempDifference] = useState(null);

    const [odenseTempAvg, setOdenseTempAvg] = useState(null);
    const [cphTempAvg, setCphTempAvg] = useState(null);
    const [tempDifferenceAvg, settempDifferenceAvg] = useState(null);


    const fetchData = async () => {
        try {
            // Fetch temperature data from backend API
            const responseOdense = await axios.get("http://localhost:8080/api/weather/odense/latest");
            const responseKøbenhavn = await axios.get("http://localhost:8080/api/weather/københavn/latest");

            const responseOdenseAvg = await axios.get("http://localhost:8080/api/weather/odense/average");
            const responseKøbenhavnAvg = await axios.get("http://localhost:8080/api/weather/københavn/average");

            // Extract temperature values from response data
            const { temperature_2m : odenseTemperature } = responseOdense.data;
            const { temperature_2m : cphTemperature } = responseKøbenhavn.data;
            const { temperature_2m_mean : odenseTemperatureAvg } = responseOdenseAvg.data;
            const { temperature_2m_mean : cphTemperatureAvg } = responseKøbenhavnAvg.data;


            // Update state with temperature values
            setOdenseTemp(odenseTemperature);
            setCphTemp(cphTemperature);

            setCphTempAvg(cphTemperatureAvg)
            setOdenseTempAvg(odenseTemperatureAvg)

            // Calculate temperature difference
            let tempDifference;
            if (cphTemperature > odenseTemperature) {
                tempDifference = cphTemperature - odenseTemperature;
            } else {
                tempDifference = odenseTemperature - cphTemperature;
            }

            settempDifference(tempDifference);

            let tempDifferenceAvg;
            if (cphTemperatureAvg > odenseTemperatureAvg) {
                tempDifferenceAvg = cphTemperatureAvg - odenseTemperatureAvg;
            } else {
                tempDifferenceAvg = odenseTemperatureAvg - cphTemperatureAvg;
            }

            settempDifferenceAvg(tempDifferenceAvg);

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




    // Return JSX
    return (
        <div className="info-box">
            <h2>Temperature Comparison (Odense/CPH)</h2>
            <div className="box-content">
                <br/>
                <p><strong>Current difference: </strong>{tempDifference !== null ? `${tempDifference.toFixed(2)} °C` : 'Loading...'} </p>
                <br/>
                <p><strong>Average difference: </strong>{tempDifferenceAvg !== null ? `${tempDifferenceAvg.toFixed(2)} °C` : 'Loading...'} </p>
            </div>
        </div>
    );
};

export default CurrentTemp;