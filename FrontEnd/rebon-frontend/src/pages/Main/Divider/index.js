import React from 'react';
import '../../../styles/main.css';

export default function Divider() {
  return (
    <div className="divider-main">
      <div className="searchTitle">'칠포해수욕장'식당</div>
      <select className="filter" name="filter">
        <option value="star" selected>
          별점순
        </option>
        <option value="review">리뷰많은 순</option>
        <option value="recent">최신순</option>
      </select>
    </div>
  );
}
