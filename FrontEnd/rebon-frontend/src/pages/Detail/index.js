import React, { useEffect, useState } from 'react';
import Slider from 'react-slick';
import Review from './Review';
import Map from './Map';
import Header from '../../components/Header';
import '../../styles/slick.css';
import '../../styles/slick-theme.css';
import '../../styles/detail.css';
import { responsiveFontSizes } from '@mui/material';
import axios from 'axios';

function SampleNextArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div className={className} style={{ ...style, display: 'block', background: '#F5F5F5', borderRadius: '20px', paddingLeft: '18px', paddingTop: '13px' }} onClick={onClick} />
  );
}

function SamplePrevArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div className={className} style={{ ...style, display: 'block', background: '#F5F5F5', borderRadius: '20px', paddingLeft: '16px', paddingTop: '13px' }} onClick={onClick} />
  );
}

export default function Detail() {
  const [shopInfo, setShopInfo] = useState([]);
  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 2,
    slidesToScroll: 2,
    centerMode: false,
    nextArrow: <SampleNextArrow />,
    prevArrow: <SamplePrevArrow />,
  };

  useEffect(() => {
    axios
      .get('http://3.34.139.61:8080/api/shops/2')
      .then((response) => {
        setShopInfo(response.data);
        console.log(shopInfo);
        console.log(shopInfo.tags);
      })
      .catch((error) => {
        console.log('error');
      });
  }, []);

  return (
    <div className="detail-wrapper">
      <Header />
      <Slider {...settings} className="detail-slider">
        <div className="detail-image-wrapper">
          <img className="detail-main-image" alt="review-image" src="image/detail.png" />
        </div>
        <div className="detail-sub-wrapper">
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
        </div>
        <div className="detail-image-wrapper">
          <img className="detail-main-image" alt="review-image" src="image/detail.png" />
        </div>
        <div className="detail-sub-wrapper">
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
          <img className="detail-sub-image" alt="review-image" src="image/detail.png" />
        </div>
      </Slider>

      <div className="detail-shop-wrapper">
        <div className="detail-title-wrapper">
          <div>
            <img className="detail-shop-icon" alt="shop-image" src="image/shop_icon.png" />
            <h3 className="detail-shop-name">{shopInfo.name}</h3>
            <div className="detail-star-wraper">
              {shopInfo.star === 0.0 ? (
                <>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo.star === 1.0 ? (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo.star === 2.0 ? (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo.star === 3.0 ? (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo.star === 4.0 ? (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo.star === 5.0 ? (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                </>
              ) : (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                </>
              )}
            </div>

            <div className="detail-shop-rating">{shopInfo.star}</div>
          </div>

          <hr></hr>
          <div className="detail-tag-wrapper">
            {/* {shopInfo.tags.length > 0 ? shopInfo.tags.map((tag) => <span className="detail-tag">{tag.name}</span>) : ''} */}
            {shopInfo.tags ? shopInfo.tags.map((tag) => <span className="detail-tag">{tag.name}</span>) : ''}
            {/* <span className="detail-tag">칠포해수욕장</span>
            <span className="detail-tag">칠포읍</span>
            <span className="detail-tag">한식</span> */}
          </div>
          <div className="detail-top-content-wrapper">
            <div className="detail-info-wrapper">
              <div className="detail-top-info-name">전화번호</div>
              <div className="detail-top-info-value">
                {shopInfo.phone}
                {/* 02-777-2254 */}
              </div>
            </div>
            <div className="detail-info-wrapper">
              <div className="detail-top-info-name">영업시간</div>
              <div className="detail-top-info-value">
                {shopInfo.businessHour}
                {/* 11:00~22:00 */}
              </div>
            </div>
          </div>
        </div>
        <div className="detail-content-wrapper">
          <div className="detail-info-wrapper">
            <div className="detail-info-name-category">업종</div>
            <div className="detail-info-category">
              {shopInfo.subCategories ? shopInfo.subCategories.map((sub) => <div className="detail-info-value-category">{sub.name}</div>) : ''}
            </div>
          </div>
          <div className="detail-info-wrapper">
            <div className="detail-info-name">영업시간</div>
            <div className="detail-info-value">{shopInfo.businessHour}</div>
          </div>
          <div className="detail-info-wrapper">
            <div className="detail-info-name">메뉴</div>
            <div className="detail-info-value">
              {shopInfo.menus
                ? shopInfo.menus.map((menu) => (
                    <>
                      <div className="detail-info-main-value">{menu.name}</div>
                      <div className="detail-info-sub-value">
                        {menu.menus.length > 0
                          ? menu.menus.map((submenu) => (
                              <>
                                <div className="detail-menu-name">{submenu.name}</div>
                                <div className="detail-menu-value">{submenu.price}</div>
                              </>
                            ))
                          : ''}
                      </div>
                    </>
                  ))
                : ''}
            </div>

            {/* <div className="detail-info-value">
              <div className="detail-info-main-value">디저트류</div>
              <div className="detail-info-sub-value">
                <div className="detail-menu-name">물회</div>
                <div className="detail-menu-value">12,000원</div>
                <div className="detail-menu-name">전복죽</div>
                <div className="detail-menu-value">14,000원</div>
                <div className="detail-menu-name">매운탕</div>
                <div className="detail-menu-value">10,000원</div>
              </div>
              <div className="detail-info-main-value">디저트류</div>
              <div className="detail-info-sub-value">
                <div className="detail-menu-name">물회</div>
                <div className="detail-menu-value">12,000원</div>
                <div className="detail-menu-name">전복죽</div>
                <div className="detail-menu-value">14,000원</div>
                <div className="detail-menu-name">매운탕</div>
                <div className="detail-menu-value">10,000원</div>
              </div>
            </div> */}
          </div>
          <div className="detail-address-wrapper">
            <div className="detail-address-name">주소</div>
            <div className="detail-address-value">
              {shopInfo.address}
              {/* 포항시 북구 흥해읍 한동로 558 한동대학교 */}
              <div className="detail-map">
                <Map searchPlace={shopInfo.address} />
                {/* <Map searchPlace="포항시 북구 흥해읍 한동로 558 한동대학교" /> */}
              </div>
            </div>
          </div>
        </div>
      </div>
      <Review />
    </div>
  );
}
