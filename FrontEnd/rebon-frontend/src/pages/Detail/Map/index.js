import React, { useEffect } from 'react';
const { kakao } = window;

export default function Map({ searchPlace, isMobile }) {
  useEffect(() => {
    const container = document.getElementById('myMap');
    const options = {
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 3,
    };
    const map = new kakao.maps.Map(container, options);

    const ps = new kakao.maps.services.Places();

    ps.keywordSearch(searchPlace, placesSearchCB);

    function placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        let bounds = new kakao.maps.LatLngBounds();

        for (let i = 0; i < data.length; i++) {
          displayMarker(data[i]);
          bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        map.setBounds(bounds);
      }
    }
    function displayMarker(place) {
      let marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x),
      });
    }
  }, [searchPlace]);

  return (
    <>
      {isMobile === '1' ? (
        <div
          id="myMap"
          style={{
            width: '100%',
            height: '150px',
            // overflow: 'visible !important',
          }}
        ></div>
      ) : isMobile === '2' ? (
        <div
          id="myMap"
          style={{
            width: '100%',
            height: '250px',
          }}
        ></div>
      ) : (
        <div
          id="myMap"
          style={{
            width: '500px',
            height: '300px',
          }}
        ></div>
      )}
    </>
  );
}
