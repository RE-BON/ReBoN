import React from 'react';
import '../../../../styles/bookmark-card.css';
import { FiHeart } from 'react-icons/fi';

export default function BookmarkCard() {
  return (
    <div className="mainCard">
      <div className="mainCard-img">
        <FiHeart md={8} size="22" />
      </div>
      <div className="titleRow">
        <div className="placeName">Place one</div>
        <div className="starNum">4.9</div>
      </div>
      <ul className="tag-wrapper">
        <li className="tag">칠포해수욕장</li>
        <li className="tag">칠포읍</li>
        <li className="tag">한식</li>
      </ul>
    </div>
  );
}
