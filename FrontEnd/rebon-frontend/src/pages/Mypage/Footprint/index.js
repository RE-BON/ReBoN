import React, { useState, useRef, useEffect } from 'react';
import FootprintModal from './FootprintModal';
import '../../../styles/footprint.css';
import ReviewStar from '../../Detail/Review/ReviewStar';
import { useMediaQuery } from 'react-responsive';
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
          setFootInfo(response.data);
          console.log("name is ",response.data[0].images[0] === null)

        })
        .catch((error) => {
          if (error.response.status === 401) return Logout;
          else console.log('Footprint error');
        });
    }
  }, []);

  function handleDelete(targetId) {
    console.log(targetId);
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };

    if (token) {
      axios
        .delete(`http://3.34.139.61:8080/api/reviews/${targetId}`, config)
        .then(function (response) {
          axios
            .get('http://3.34.139.61:8080/api/my-reviews', config)
            .then((response) => {
              setFootInfo(response.data);
            })
            .catch((error) => {
              if (error.response.status === '401') return Logout;
              else console.log(error);
            });
        })
        .catch(function (error) {
          throw new Error(error);
        });
    }
  }
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
                            <FootprintModal info={info} handleDelete={handleDelete} />
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
                          {info.images && info.images[0]!==null? info.images.map((item, index)  => {
                            const url = `https://rebon.s3.ap-northeast-2.amazonaws.com/${item}`
                            return (
                              <div className="footprint-image-wrapper">

                              <div className="footprint-image-sub-wrapper">
                                <img className="footprint-image" alt="footprint-img" src= {url}/>
                              </div>
                              </div>

                            );
                          }) : ''}

                      </div>
                      {/*<div className="footprint-image-sub-wrapper">*!/*/}
                      {/*  <img className="footprint-image" alt="footprint-img" src= {url}/>*/}
                      {/*</div>*/}
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
                          <FootprintModal info={info} handleDelete={handleDelete} />
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
                        {/*{info.image ? (*/}
                        {/*    <div className="footprint-image-wrapper">*/}
                        {/*      {}*/}
                        {/*    </div>*/}
                        {/*) : ''}*/}
                        {info.images && info.images[0]!==null ? info.images.map((item, index)  => {
                          const url = `https://rebon.s3.ap-northeast-2.amazonaws.com/${item}`
                          return (
                            <div className="footprint-image-wrapper">
                              <div className="footprint-image-sub-wrapper">
                                <img className="footprint-image" alt="사진을 가져오지 못했습니다." src= {url}/>
                              </div>
                            </div>

                          );
                        }) : ''}
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
