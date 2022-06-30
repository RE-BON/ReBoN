import React, { useState, useRef, useEffect } from 'react';
import FootprintModal from './FootprintModal';
import '../../../styles/footprint.css';
import ReviewStar from '../../Detail/Review/ReviewStar';

export default function Footprint() {
  const [footInfo, setFootInfo] = useState([
    {
      id: 1,
      shopName: '버거킹',
      content: '맛이 좋아요',
      tip: '필수로 시키자',
      star: 5,
      empathyCount: 0,
      images: [],
    },
    {
      id: 2,
      shopName: '팜스발리',
      content: '맛이 별로예요',
      tip: '김치 불고기 피자가 제일 맛있어요',
      star: 2,
      empathyCount: 6,
      images: [],
    },
    {
      id: 3,
      shopName: '애슐리',
      content:
        '피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요',
      tip: null,
      star: 4,
      empathyCount: 20,
      images: [],
    },
  ]);
  return (
    <div className="footprint-container">
      <div className="footprint-title">발자국</div>
      <div className="footprint-wrapper">
        {footInfo.length > 0
          ? footInfo.map((info) => (
              <>
                <div className="footprint-content">
                  <div className="footprint-remove-wrapper">
                    <FootprintModal />
                  </div>
                  <div className="footprint-user">
                    <span className="footprint-user-name">{info.shopName}</span>
                    <span className="footprint-rating">
                      <ReviewStar star={info.star} />
                    </span>
                    {info.tip ? (
                      <div className="footprint-tip-wrapper">
                        <span className="footprint-tip-name">나만의 꿀팁</span>
                        <span className="footprint-tip-content">{info.tip}</span>
                      </div>
                    ) : (
                      ''
                    )}

                    <div className="footprint-post">{info.content}</div>
                  </div>
                  <div className="footprint-image-wrapper">
                    <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                    <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                    <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                    <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                  </div>
                </div>
                <div className="footprint-light-hr" />
              </>
            ))
          : ''}
      </div>
    </div>
  );
}
