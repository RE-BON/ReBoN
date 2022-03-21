import React from 'react';
import '../../styles/footprint.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleUser, faShareNodes, faEllipsisVertical } from '@fortawesome/free-solid-svg-icons';
import { faHeart, faPenToSquare } from '@fortawesome/free-regular-svg-icons';

export default function Footprint() {
  return (
    <div className="footprint-wrapper">
      <h4 className="footprint-name">발자국</h4>
      <hr className="footprint-bold-hr" />

      <div className="footprint-content">
        <div className="footprint-remove-wrapper">
          <span className="footprint-remove-button">리뷰삭제</span>
        </div>
        <div className="footprint-user">
          <span className="footprint-user-name">파파야리프</span>
          <span className="footprint-rating">4.0</span>
          <span className="footprint-date">2022.03.14</span>
          <div className="footprint-tip-wrapper">
            <span className="footprint-tip-name">나만의 꿀팁</span>
            <span className="footprint-tip-content">2층 창가자리 추천이요!</span>
          </div>
          <div className="footprint-post">
            중구 근처 레스토랑 찾다가 와봤는데 분위기도 너무 좋고 맛도 너무 좋네요 !! 아이들이랑 왔는데 식기도 따로 주시고 직원분들 서비스, 맛 하나 빠지는게 없이 식사 만족스럽게 잘
            했습니다. 양식 드시고 싶다면 한번씩 와보시는거 왕 추천 ~~!!
          </div>
        </div>
      </div>
      <hr className="footprint-light-hr" />

      <div className="footprint-content">
        <div className="footprint-remove-wrapper">
          <span className="footprint-remove-button">리뷰삭제</span>
        </div>
        <div className="footprint-user">
          <span className="footprint-user-name">파파야리프</span>
          <span className="footprint-rating">4.0</span>
          <span className="footprint-date">2022.03.14</span>
          <div className="footprint-tip-wrapper">
            <span className="footprint-tip-name">나만의 꿀팁</span>
            <span className="footprint-tip-content">2층 창가자리 추천이요!</span>
          </div>
          <div className="footprint-post">
            중구 근처 레스토랑 찾다가 와봤는데 분위기도 너무 좋고 맛도 너무 좋네요 !! 아이들이랑 왔는데 식기도 따로 주시고 직원분들 서비스, 맛 하나 빠지는게 없이 식사 만족스럽게 잘
            했습니다. 양식 드시고 싶다면 한번씩 와보시는거 왕 추천 ~~!!
          </div>
        </div>
        <div className="footprint-image-wrapper">
          <img className="footprint-image" alt="footprint-image" src="image/detail.png" />
          <img className="footprint-image" alt="footprint-image" src="image/detail.png" />
          <img className="footprint-image" alt="footprint-image" src="image/detail.png" />
          <img className="footprint-image" alt="footprint-image" src="image/detail.png" />
        </div>
      </div>
      <hr className="footprint-light-hr" />
    </div>
  );
}
