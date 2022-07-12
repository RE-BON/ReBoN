import React, { useState, useLayoutEffect } from 'react';
import '../../../styles/main.css';
import Toggle from 'react-toggle';
import MainCard from '../MainCard';
import axios from 'axios';

export default function Divider({ shopInfo, tagId, subId }) {
  const [toggleOn, setToggleOn] = useState(false);
  const [sort, setSort] = useState('star');
  const [openShopInfo, setOpenShopInfo] = useState();
  const [ready, setReady] = useState(false);

  useLayoutEffect(() => {
    var url = 'http://3.34.139.61:8080/api/shops?tag=' + tagId + '&category=1&subCategories=' + subId + '&open=true';

    axios
      .get(url)
      .then((response) => {
        console.log('devider:open:,subId is: ', subId, 'tagId is', tagId, 'url is: ', url, ' result is: ', response.data);
        setOpenShopInfo(response.data);
        setReady(true);
      })
      .catch((error) => {
        console.log('error');
      });
  }, []);

  const toggleChange = (event) => {
    if (toggleOn) setToggleOn(false);
    else setToggleOn(true);
  };

  return (
    <>
      {ready ? (
        <>
          <div className="divider-main">
            <div className="divider-right">
              <Toggle className="main-toggle" id="" defaultChecked={toggleOn} onChange={toggleChange} />
              <span className="main-toggle-label">현재 영업중</span>
              <select
                className="filter"
                name="filter"
                onChange={(e) => {
                  setSort(e.target.value);
                }}
              >
                <option value="star" selected>
                  별점순
                </option>
                <option value="review">리뷰많은 순</option>
                <option value="recent">최신순</option>
              </select>
            </div>
          </div>
          <div className="mainCard-wrapper">
            {toggleOn ? <MainCard sort={sort} mainInfo={openShopInfo} /> : <MainCard sort={sort} mainInfo={shopInfo} />}
            {/* <MainCard sort={sort} /> */}
          </div>
        </>
      ) : (
        ''
      )}
    </>
  );
}
