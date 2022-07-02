import React, { useEffect, useState } from 'react';
import Slider from 'react-slick';
import Review from './Review';
import Map from './Map';
import Header from '../../components/Header';
import '../../styles/detail.css';
import { useMediaQuery } from 'react-responsive';
import axios from 'axios';
import Carousel from './Carousel';

export default function Detail() {
  // const [shopInfo, setShopInfo] = useState();
  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });
  const isTablet = useMediaQuery({
    query: '(min-width:768px) and (max-width:991px)',
  });
  const [shopInfo, setShopInfo] = useState({
    id: 1,
    name: '팜스발리',
    star: 5.0,
    like: true,
    businessHour: '08:00~22:00',
    tags: [
      {
        id: 1,
        name: '포항',
      },
      {
        id: 3,
        name: '한동대',
      },
    ],
    phone: '010-1234-5678',

    subCategories: [
      {
        id: 9,
        name: '식당',
      },
    ],
    address: '경상북도 포항시 북구 흥해읍 한동로 558',
    menus: [
      {
        name: '피자류',
        menus: [
          {
            name: '김치 불고기',
            price: 13700,
          },
          {
            name: '고구마',
            price: 11000,
          },
        ],
      },

      {
        name: '치킨류',
        menus: [
          {
            name: '허니 치킨',
            price: 13700,
          },
          {
            name: '후라이드 치킨',
            price: 11000,
          },
        ],
      },
    ],
    image: 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Supreme_pizza.jpg/800px-Supreme_pizza.jpg',
  });

  // useEffect(() => {
  //   axios
  //     .get('http://3.34.139.61:8080/api/shops/1')
  //     .then((response) => {
  //       setShopInfo(response.data);
  //       console.log(shopInfo);
  //       console.log(response.data);
  //     })
  //     .catch((error) => {
  //       console.log('error');
  //     });
  // }, []);

  return (
    <div className="detail-wrapper">
      <Header />
      {/* {isMobile} */}
      {isMobile ? <Carousel isMobile="1" imageInfo={shopInfo.image} /> : <Carousel isMobile="0" imageInfo={shopInfo.image} />}
      <div className="detail-shop-wrapper">
        <div className="detail-title-wrapper">
          <div className="detail-shop-star-wrapper">
            <img className="detail-shop-icon" alt="shop-image" src="/image/shop_icon.png" />

            {shopInfo.name ? <h3 className="detail-shop-name">{shopInfo.name}</h3> : ''}
            <div className="detail-star-wrapper">
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
              <div className="detail-shop-rating">{shopInfo.star}.0</div>
            </div>
          </div>

          <hr></hr>
          <div className="detail-tag-wrapper">
            {/* {shopInfo.tags.length > 0 ? shopInfo.tags.map((tag) => <span className="detail-tag">{tag.name}</span>) : ''} */}
            {shopInfo.tags ? shopInfo.tags.map((tag) => <span className="detail-tag">{tag.name}</span>) : ''}
          </div>
          <div className="detail-top-content-wrapper">
            <div className="detail-info-top-wrapper">
              <div className="detail-top-info-name">전화번호</div>
              <div className="detail-top-info-value">
                {shopInfo.phone} ☏{/* 02-777-2254 */}
              </div>
            </div>
            <div className="detail-info-top-wrapper">
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
            <div className="detail-info-name">메뉴</div>
            <div className="detail-info-value">
              {shopInfo.menus
                ? shopInfo.menus.map((menu) => (
                    <>
                      <div className="detail-info-main-value">{menu.name}</div>
                      {menu.menus.length > 0
                        ? menu.menus.map((submenu) => (
                            <div className="detail-info-sub-value">
                              <div className="detail-menu-name">{submenu.name}</div>
                              <div className="detail-menu-value">{submenu.price.toLocaleString()}원</div>
                            </div>
                          ))
                        : ''}
                    </>
                  ))
                : ''}
            </div>
          </div>
          <div className="detail-address-wrapper">
            <div className="detail-address-name">주소</div>
            <div className="detail-address-value">
              {shopInfo.address}
              <div className="detail-map">
                {isMobile ? (
                  <Map searchPlace={shopInfo.address} isMobile="1" />
                ) : isTablet ? (
                  <Map searchPlace={shopInfo.address} isMobile="2" />
                ) : (
                  <Map searchPlace={shopInfo.address} isMobile="0" />
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
      <Review />
    </div>
  );
}
