import React from 'react';
import '../../../styles/main.css';

export default function MainCard() {
  return (
    <div className="mainCard">
      <div className="mainCard-img"></div>
      <div className="titleRow">
        <div className="placeName">Place one</div>
        <div className="starNum">4.9</div>
      </div>
      <div className="tagRow">
        <span className="tag">칠포해수욕장</span>
        <span className="tag">칠포읍</span>
        <span className="tag">한식</span>
      </div>
    </div>
  );
}
