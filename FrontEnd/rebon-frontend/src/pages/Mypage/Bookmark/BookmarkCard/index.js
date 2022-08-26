import React from 'react';
import '../../../../styles/bookmark-card.css';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';

export default function BookmarkCard() {
  return (
    <>
      <div className="bookmarkCard">
        <div className="bookmarkCard-img">
          <FiHeart md={8} size="22" />
        </div>
        <div className="bookmarkCard-bottom">
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
      </div>
      {/* /////////////////////// 모바일 /////////////////////// */}
      <div className="bookmarkCard-mobile">
        {/* <img class="main-img" src={item.image} /> */}

        {/* <div className="likeBtn-main">
          <FaHeart className="heart-icon" md={8} size="22" onClick={likeClick} />
      </div>
      <div className="mainCard-bottom">
        <div className="titleRow">
            <div className="placeName-main">식당이름</div>
          <div className="starNum">5</div>
        </div>
        <div className="">
            <span className="tag">한동대</span>
        </div>
      </div> */}
      </div>
    </>
  );
}
