import React, { useState, useRef, useEffect } from 'react';
import FootprintModal from './FootprintModal';
import '../../../styles/footprint.css';
import ReviewStar from '../../Detail/Review/ReviewStar';
import { useMediaQuery } from 'react-responsive';
import ReviewModal from '../../Detail/Review/ReviewModal';
import Logout from '../../Logout';
import axios from 'axios';

export default function Footprint() {
  const [footInfo, setFootInfo] = useState();

  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });

  const [token, setToken] = useState(window.sessionStorage.getItem('token'));

  useEffect(() => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    if (token) {
      axios
        .get('http://3.34.139.61:8080/api/my-reviews', config)
        .then((response) => {
          console.log('Footprint: ', response.data);
          setFootInfo(response.data);
        })
        .catch((error) => {
          if (error.response.status === 401) return Logout;
          else console.log('Footprint error');
        });
    }
  }, []);
  // const [footInfo, setFootInfo] = useState([
  //   {
  //     id: 1,
  //     shopName: '버거킹',
  //     content: '맛이 좋아요',
  //     tip: '필수로 시키자',
  //     star: 5,
  //     empathyCount: 0,
  //     images: [],
  //   },
  //   {
  //     id: 2,
  //     shopName: '팜스발리',
  //     content: '맛이 별로예요',
  //     tip: '김치 불고기 피자가 제일 맛있어요',
  //     star: 2,
  //     empathyCount: 6,
  //     images: [],
  //   },
  //   {
  //     id: 3,
  //     shopName: '애슐리',
  //     content:
  //       '피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요 피자도 맛있고 치킨도 맛있어요',
  //     tip: null,
  //     star: 4,
  //     empathyCount: 20,
  //     images: [],
  //   },
  // ]);
  return (
    <>
      {footInfo ? (
        isMobile ? (
          <div className="footprint-container">
            <div className="footprint-wrapper">
              {footInfo.length > 0
                ? footInfo.map((info) => (
                    <>
                      <div className="footprint-content">
                        <div className="footprint-user">
                          <div className="footprint-list-icon">
                            <FootprintModal />
                          </div>
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
                          <div className="footprint-image-sub-wrapper">
                            <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                          </div>
                          <div className="footprint-image-sub-wrapper">
                            <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                          </div>
                        </div>
                      </div>
                      <div className="footprint-light-hr" />
                    </>
                  ))
                : ''}
            </div>
          </div>
        ) : (
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
                          <div className="footprint-image-sub-wrapper">
                            <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                          </div>
                          <div className="footprint-image-sub-wrapper">
                            <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                          </div>
                          <div className="footprint-image-sub-wrapper">
                            <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                          </div>
                          <div className="footprint-image-sub-wrapper">
                            <img className="footprint-image" alt="footprint-img" src="../../../../image/detail.png" />
                          </div>
                        </div>
                      </div>
                      <div className="footprint-light-hr" />
                    </>
                  ))
                : ''}
            </div>
          </div>
        )
      ) : (
        ''
      )}
    </>
  );
}
