import React from 'react';
import '../../assets/css/slick.css';
import '../../assets/css/slick-theme.css';
import Slider from 'react-slick';
import Review from './Review';
import '../../assets/css/detail.css';

function Detail() {
  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 1,
    centerMode: true,
  };

  return (
    <div className="detail-wrapper">
      <div className="detail-image-wrapper">
        <img className="detail-main-image" alt="review-image" src="image/detail.png" />
        <div className="detail-sub-wrapper">
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
        </div>
      </div>
      <div className="detail-shop-wrapper">
        <div className="detail-title-wrapper">
          <h3 className="detail-shop-name">마라도 물회</h3>
          <h3 className="detail-shop-rating">4.9</h3>
          <div className="detail-tag-wrapper">
            <span className="detail-tag">칠포해수욕장</span>
            <span className="detail-tag">칠포읍</span>
            <span className="detail-tag">한식</span>
          </div>
        </div>
        <div className="detail-content-wrapper">
          <div className="detail-info-wrapper">
            <div className="detail-info-name">전화번호</div>
            <div className="detail-info-value">02-777-2254</div>
          </div>
          <div className="detail-info-wrapper">
            <div className="detail-info-name">업종</div>
            <div className="detail-info-value">한식</div>
          </div>
          <div className="detail-info-wrapper">
            <div className="detail-info-name">주차</div>
            <div className="detail-info-value">주차가능</div>
          </div>
          <div className="detail-info-wrapper">
            <div className="detail-info-name">영업시간</div>
            <div className="detail-info-value">11:00~20:00</div>
          </div>
          <div className="detail-info-wrapper">
            <div className="detail-info-name">메뉴</div>
            <div className="detail-info-value">
              <div className="detail-menu-name">물회</div>
              <div className="detail-menu-value">12,000원</div>
              <div className="detail-menu-name">전복죽</div>
              <div className="detail-menu-value">14,000원</div>
              <div className="detail-menu-name">매운탕</div>
              <div className="detail-menu-value">10,000원</div>
            </div>
          </div>
          <div className="detail-address-wrapper">
            <div className="detail-address-name">주소</div>
            <div className="detail-address-value">
              포항시 북구 장성동 14번길 45
              <div className="detail-map">
                <img className="detail-map-image" alt="detail-map" src="image/detail-map.png" />
              </div>
            </div>
          </div>
        </div>
      </div>
      <Review />
    </div>
  );
}

export default Detail;
