import React, { useState, useEffect } from 'react';
import '../../../styles/main.css';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';
import Loading from '../../Login/Loading';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

export default function MainCard({ tagId, cateId, data, checked, open, sort, like, changeLike }) {
  const [mainInfo, setMainInfo] = useState(null);
  const [subId, setSubId] = useState();
  const token = window.sessionStorage.getItem('token');
  const [ready, setReady] = useState(false);

  const [isOpen, setIsOpen] = useState(false);
  const [opacity, setOpacity] = useState(0);

  const config = {
    headers: { Authorization: `Bearer ${token}` },
  };

  useEffect(() => {
    setReady(false);
    setTimeout(function () {
      if (data) {
        var url = 'http://3.34.139.61:8080/api/shops?tag=' + tagId + '&category=' + cateId + '&subCategories=' + checked + '&open=' + open + '&sort=' + sort + '%2Cdesc';

        axios.get(url, config).then((response) => {
          setMainInfo(response.data);
          setReady(true);
        });
      }
    }, 400);
  }, [data, checked, open, sort, cateId]);

  const likeClick = (shopId, idx, e) => {
    if (token) {
      if (like[idx]) {
        changeLike(idx);

        if (token) {
          var url = 'http://3.34.139.61:8080/api/shops/' + shopId + '/unlike';
          axios
            .post(
              url,
              {
                likeCount: 0,
                like: false,
              },
              config
            )
            .then((response) => {
              console.log(response);
            })
            .catch((error) => {
              console.log(error);
            });
        }
      } else {
        changeLike(idx);

        const config = {
          headers: { Authorization: `Bearer ${token}` },
        };
        if (token) {
          var url = 'http://3.34.139.61:8080/api/shops/' + shopId + '/like';
          axios
            .post(
              url,
              {
                likeCount: 1,
                like: true,
              },
              config
            )
            .then((response) => {
              console.log(response);
            })
            .catch((error) => {
              console.log(error);
            });
        }
      }
    } else {
      // alert('로그인 후 좋아요를 눌러주세요 :)');
      toggleModal();
    }
  };

  function toggleModal(e) {
    setOpacity(0);
    setIsOpen(!isOpen);
  }

  function afterOpen() {
    setTimeout(() => {
      setOpacity(1);
    }, 100);
  }

  function beforeClose() {
    return new Promise((resolve) => {
      setOpacity(0);
      setTimeout(resolve, 300);
    });
  }

  const FadingBackground = styled(BaseModalBackground)`
    /* opacity: ${(props) => props.opacity}; */
    transition: all 0.3s ease-in-out;
    /* background: */
    background-color: rgba(0, 0, 0, 0.15);
    opacity: 0.8;
  `;

  return (
    <>
      {ready ? (
        mainInfo ? (
          mainInfo.map((item, idx) => {
            var address = '/detail/' + item.id.toString();
            var star = item.star.toFixed();

            return (
              <div className="mainCard">
                {item.image ? (
                  <img className="main-img" src={item.image} />
                ) : (
                  <img
                    className="main-img"
                    src="https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg"
                  />
                )}
                <div className="likeBtn-main">
                  {like[idx] ? (
                    <FaHeart
                      className="heart-icon"
                      md={8}
                      size="22"
                      onClick={(e) => {
                        likeClick(item.id, idx, e);
                      }}
                    />
                  ) : (
                    <FiHeart
                      className="heart-icon-fi"
                      md={8}
                      size="22"
                      onClick={(e) => {
                        likeClick(item.id, idx, e);
                      }}
                    />
                  )}
                </div>

                <div className="mainCard-bottom">
                  <div className="titleRow">
                    <Link to={address} style={{ color: 'inherit', textDecoration: 'none' }}>
                      <div className="placeName-main">{item.name}</div>
                    </Link>
                    <div className="starNum">{star}.0</div>
                  </div>
                  {/* <div className="">
                        {item.tags.map((tag) => (
                          <span className="tag">{tag.name}</span>
                        ))}
                      </div> */}
                </div>
                <ModalProvider backgroundComponent={FadingBackground}>
                  <StyledModal
                    isOpen={isOpen}
                    afterOpen={afterOpen}
                    beforeClose={beforeClose}
                    onBackgroundClick={toggleModal}
                    onEscapeKeydown={toggleModal}
                    opacity={opacity}
                    backgroundProps={{ opacity }}
                  >
                    <div className="post-modal-wrapper">
                      <button className="close" onClick={toggleModal}>
                        <FontAwesomeIcon icon={faXmark} />
                      </button>
                      <img className="post-modal-image" alt="review-image" src="/image/reviewLogo.png" />
                      <div className="post-modal-notice">로그인 후 좋아요를 눌러주세요//:)</div>
                    </div>
                  </StyledModal>
                </ModalProvider>
              </div>
            );
          })
        ) : (
          <Loading />
        )
      ) : mainInfo ? (
        <Loading />
      ) : (
        ''
      )}

      {/*{*/}
      {/*}*/}
    </>
  );
}
const StyledModal = Modal.styled`
  width: 21rem;
  height: 16rem;
  padding : 20px;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;`;
