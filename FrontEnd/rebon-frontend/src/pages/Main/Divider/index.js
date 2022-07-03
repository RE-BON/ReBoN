import React, { useState } from 'react';
import '../../../styles/main.css';
import Toggle from 'react-toggle';
import MainCard from '../MainCard';

export default function Divider() {
  const [toggleOn, setToggleOn] = useState(false);
  const [sort, setSort] = useState('star');

  const toggleChange = (event) => {
    if (toggleOn) setToggleOn(false);
    else setToggleOn(true);
    // console.log(event);
  };
  return (
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
        <MainCard sort={sort} />
      </div>
    </>
  );
}
