import React, { useEffect, useState } from 'react';
import Slider from 'react-slick';
import Review from './Review';
import Map from './Map';
import Header from '../../components/Header';
import '../../styles/detail.css';
import { useMediaQuery } from 'react-responsive';
import axios from 'axios';
import Carousel from './Carousel';
import Footer from '../../components/Footer';
import { useLocation } from 'react-router';

export default function Detail() {
  const [shopInfo, setShopInfo] = useState();
  const location = useLocation();
  const [shopNum, setShopNum] = useState();
  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });
  const isTablet = useMediaQuery({
    query: '(min-width:768px) and (max-width:991px)',
  });
  const [shopInfo2, setShopInfo2] = useState({
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

  useEffect(() => {
    console.log("Detail!");
    setShopNum(Number(location.pathname.slice(8)));
    const shopNumber = Number(location.pathname.slice(8));
    console.log("shopNumber is ",shopNumber);
    axios
      // .get('http://3.34.139.61:8080/api/shops/' + location.pathname.slice(8))
      .get(`http://3.34.139.61:8080/api/shops/${shopNumber}`)
      .then((response) => {
        console.log('shopInfo is ',response.data);
        console.log("shopInfo menu is ",response.data.menus);
        setShopInfo(response.data);
      })
      .catch((error) => {
        console.log('Detail error');
      });
  }, []);

  return (
    <div className="detail-wrapper">
      <Header />
      {shopInfo?(
        isMobile ? <Carousel isMobile="1" imageInfo={shopInfo?.images} imageLength={shopInfo?.images.length} /> : <Carousel isMobile="0" imageInfo={shopInfo?.images[0].url} />
      ):''}
      {/*{shopInfo && isMobile ? <Carousel isMobile="1" imageInfo={shopInfo?.images} imageLength={shopInfo?.images.length} /> : <Carousel isMobile="0" imageInfo={shopInfo?.images[0].url} />}*/}
      <div className="detail-shop-wrapper">
        <div className="detail-title-wrapper">
          <div className="detail-shop-star-wrapper">
            <img className="detail-shop-icon" alt="shop-image" src="/image/shop_icon.png" />

            {shopInfo?.name ? <h3 className="detail-shop-name">{shopInfo?.name}</h3> : ''}
            <div className="detail-star-wrapper">
              {shopInfo?.star === 0 ? (
                <>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo?.star === 1 ? (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo?.star === 2 ? (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo?.star === 3 ? (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo?.star === 4 ? (
                <>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star-point">★</label>
                  <label className="detail-star">★</label>
                </>
              ) : shopInfo?.star === 5 ? (
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
              <div className="detail-shop-rating">{shopInfo?.star}.0</div>
            </div>
          </div>

          <hr></hr>
          <div className="detail-tag-wrapper">
            {/* {shopInfo.tags.length > 0 ? shopInfo.tags.map((tag) => <span className="detail-tag">{tag.name}</span>) : ''} */}
            {shopInfo?.tags ? shopInfo?.tags.map((tag,index) => <span className="detail-tag" key={index}>{tag.name}</span>) : ''}
          </div>
          <div className="detail-top-content-wrapper">
            <div className="detail-info-top-wrapper">
              <div className="detail-top-info-name">전화번호</div>
              <div className="detail-top-info-value">
                {shopInfo?.phone} <span className="detail-phone-icon">☏</span>
              </div>
            </div>
            <div className="detail-info-top-wrapper">
              <div className="detail-top-info-name">영업시간</div>
              <div className="detail-top-info-value">{shopInfo?.businessHour}</div>
            </div>
          </div>
        </div>
        <div className="detail-content-wrapper">
          <div className="detail-info-wrapper">
            <div className="detail-info-name-category">업종</div>
            <div className="detail-info-category">
              {shopInfo?.subCategories ? shopInfo?.subCategories.map((sub,index) => <div className="detail-info-value-category" key={index}>{sub.name}</div>) : ''}
            </div>
          </div>

          <div className="detail-info-wrapper">
            <div className="detail-info-name">메뉴</div>
            <div className="detail-info-value">
              {shopInfo?.menus
                ? shopInfo.menus.map((menu,index) => (
                    <>
                      {index%5===0?<hr style={{width:'45%'}}/>:''}
                      <div className="detail-info-sub-value" key={index}>
                        <div className="detail-menu-name">{menu.name}</div>
                        <div className="detail-menu-value">{menu.price.toLocaleString()}원</div>
                      </div>
                      </>
                  ))
                : ''}
              <hr style={{width:'45%'}}/>
            </div>
          </div>
          <div className="detail-address-wrapper">
            <div className="detail-address-name">주소</div>
            <div className="detail-address-value">
              {shopInfo?.address}
              {/*<div className="detail-map">*/}
              {/*  {isMobile ? (*/}
              {/*    <Map searchPlace={shopInfo?.address} isMobile="1" />*/}
              {/*  ) : isTablet ? (*/}
              {/*    <Map searchPlace={shopInfo?.address} isMobile="2" />*/}
              {/*  ) : (*/}
              {/*    <Map searchPlace={shopInfo?.address} isMobile="0" />*/}
              {/*  )}*/}
              {/*</div>*/}
            </div>
          </div>
        </div>
      </div>
      <Review shopName={shopInfo?.name} shopImage={shopInfo?.image} shopId={location.pathname.slice(8)} />
      <Footer />
    </div>
  );
}
