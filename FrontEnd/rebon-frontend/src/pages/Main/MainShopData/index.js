import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router';
import Main from '../../Main';
import Loading from '../../Login/Loading';

export default function MainShopData({ restCategory, accoCategory, cafeCategory }) {
  const location = useLocation();
  const [ready, setReady] = useState(false);
  const [restData, setRestData] = useState(null);
  const [accoData, setAccoData] = useState(null);
  const [cafeData, setCafeData] = useState(null);
  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  const config = {
    headers: { Authorization: `Bearer ${token}` },
  };

  useEffect(() => {
    setReady(false);

    var restArr = [];
    var accoArr = [];
    var cafeArr = [];

    const noRest = [74, 91, 29];
    if (!noRest.includes(data.id)) {
      var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=1&subCategories=' + data.id + '&open=false&sort=shopScore.star%2Cdesc';
      axios
        .get(url, config)
        .then((response) => {
          const restState = { id: data.id, shop: response.data };
          restArr.push(restState);
        })
        .catch((error) => {
          if (error.response.status === 500) {
            restArr.push([]);
          } else console.log('restShop error');
        });
    }

    setRestData(restArr);

    cafeCategory.map((data, index) => {
      const noCafe = [96];
      if (!noCafe.includes(data.id)) {
        var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=2&subCategories=' + data.id + '&open=false&sort=shopScore.star%2Cdesc';
        axios
          .get(url, config)
          .then((response) => {
            const cafeState = { id: data.id, shop: response.data };
            cafeArr.push(cafeState);
          })
          .catch((error) => {
            if (error.response.status === 500) {
              cafeArr.push([]);
            } else console.log('cafeShop error');
          });
      }
    });
    setCafeData(cafeArr);

    accoCategory.map((data, index) => {
      const noAcco = [100, 102, 99, 101, 18, 98, 17, 16];
      if (!noAcco.includes(data.id)) {
        var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=3&subCategories=' + data.id + '&open=false&sort=shopScore.star%2Cdesc';
        axios
          .get(url, config)
          .then((response) => {
            const accoState = { id: data.id, shop: response.data };
            accoArr.push(accoState);
          })
          .catch((error) => {
            if (error.response.status === 500) {
              accoArr.push([]);
            } else console.log('accoShop error');
          });
      }
    });
    setAccoData(accoArr);
    setReady(true);
  }, []);

  return (
    <>
      {ready ? (
        <Main restCategory={restCategory} accoCategory={accoCategory} cafeCategory={cafeCategory} restData={restData} accoData={accoData} cafeData={cafeData} />
      ) : (
        <Loading />
      )}
    </>
  );
}
