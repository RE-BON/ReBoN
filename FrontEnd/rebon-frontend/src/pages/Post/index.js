import '../../styles/post.css';
import '../../styles/post-star.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { useMediaQuery } from 'react-responsive';
import Header from '../../components/Header';
import PostModal from './PostModal';
// import Logout from '../Logout';
import { useState } from 'react';
import axios from 'axios';
import AWS from 'aws-sdk';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { Link } from 'react-router-dom';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { useLocation } from 'react-router';


export default function Post() {
  const location = useLocation();
  const shopId = Number(location.pathname.slice(6));
  console.log(shopId);

  //별 state
  const [pharase, setPharase] = useState(null);
  let state = pharase;
  const [starRate, setStarRate] = useState(null);

  //나만의 꿀팁 state
  const [myTip, setMyTip] = useState('');
  const onChangeMyTip = (e) => {
    setMyTip(e.target.value);
  };

  //콘텐츠 state
  const [myContent, setMyContent] = useState('');
  const onChangeMyContent = (e) => {
    setMyContent(e.target.value);
  };

  //이미지 state
  const [imageSrc, setImageSrc] = useState('');
  const [file, setFile] = useState();
  const [fileName, setFileName] = useState();
  const [isOpen, setIsOpen] = useState(false);
  const [opacity, setOpacity] = useState(0);

  const region = 'ap-northeast-2';
  const bucket = 'rebon';

  AWS.config.update({
    region: region,
    accessKeyId: 'AKIAWHWKYOWONYWK7TQK',
    secretAccessKey: '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',
  });

  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });

  const preview = (fileBlob) => {
    const reader = new FileReader();
    reader.readAsDataURL(fileBlob);

    return new Promise((resolve) => {
      reader.onload = () => {
        setImageSrc(reader.result);
        resolve();
      };
    });
  };

  const imgUpload = async (e) => {
    const upload = new AWS.S3.ManagedUpload({
      params: {
        Bucket: bucket, // 버킷 이름
        Key: fileName,
        Body: file, // 파일 객체
      },
    });

    const promise = upload.promise();
    promise.then(
      function () {
        // 이미지 업로드 성공
        // setOpacity(0);
        // setIsOpen(!isOpen);
      },
      function (err) {
        // 이미지 업로드 실패
        alert('이미지 업로드 실패');
      }
    );
  };

  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  const postSubmit = () => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    axios
      .post(
        `http://3.34.139.61:8080/api/shops/${shopId}/reviews`,
        //1에 ${shopId}
        {
          content: myContent,
          tip: myTip,
          imageUrls: [],
          star: starRate,
        },
        config
      )
      .then(function (response) {
        console.log(response.data);
        console.log('shopId', shopId);
      })
      .catch(function (error) {
        if (error.response.status === 400) {
          alert(error.response.data.message);
        } else console.log(error);
      });
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
    opacity: ${(props) => props.opacity};
    transition: all 0.3s ease-in-out;
  `;

  return (
    <>
      {isMobile ? <div></div> : <Header />}

      <div className="post-wrapper">
        <div className="post-star">
          <form name="myform" id="myform" method="post" action="./save">
            <div className="star-wrapper">
              <fieldset>
                <input
                  onClick={() => {
                    setPharase('최고예요.');
                    setStarRate(5);
                  }}
                  type="radio"
                  name="rating"
                  value="5"
                  id="rate1"
                />
                <label htmlFor="rate1">★</label>
                <input
                  onClick={() => {
                    setPharase('좋아요.');
                    setStarRate(4);
                  }}
                  type="radio"
                  name="rating"
                  value="4"
                  id="rate2"
                />
                <label htmlFor="rate2">★</label>
                <input
                  onClick={() => {
                    setPharase('괜찮아요.');
                    setStarRate(3);
                  }}
                  type="radio"
                  name="rating"
                  value="3"
                  id="rate3"
                />
                <label htmlFor="rate3">★</label>
                <input
                  onClick={() => {
                    setPharase('그저 그래요.');
                    setStarRate(2);
                  }}
                  type="radio"
                  name="rating"
                  value="2"
                  id="rate4"
                />
                <label htmlFor="rate4">★</label>
                <input
                  onClick={() => {
                    setPharase('별로예요.');
                    setStarRate(1);
                  }}
                  type="radio"
                  name="rating"
                  value="1"
                  id="rate5"
                />
                <label htmlFor="rate5">★</label>
              </fieldset>
              <div className="star-wrapper-pharases">{state == null ? null : <span>{pharase}</span>}</div>
            </div>
          </form>
        </div>

        {isMobile ? (
          <div className="post-tip-mobile">
            <div className="post-tip-box-mobile">나만의 꿀팁</div>
            <input onChange={onChangeMyTip} value={myTip} placeholder="''마라도 횟집''에 대한 나만의 꿀팁을 적어주세요(ex.2층 창가자리 뷰가 예뻐요)" maxLength="500" />
          </div>
        ) : (
          <div className="post-tip">
            <div className="post-tip-box">나만의 꿀팁</div>
            <input onChange={onChangeMyTip} value={myTip} placeholder="''마라도 횟집''에 대한 나만의 꿀팁을 적어주세요(ex.2층 창가자리 뷰가 예뻐요)" maxLength="500" />
          </div>
        )}

        <div className="post-tip-count">{myTip.length}/500</div>

        <div className="post-review">
          <textarea rows="6" onChange={onChangeMyContent} value={myContent} placeholder="  ''마라도 횟집''에 대한 리뷰를 적어주세요" maxLength="500"></textarea>
          <div className="post-review-count">{myContent.length}/1000</div>
        </div>

        <div className="post-attach">
          <input
            type="file"
            id="input-file"
            accept=".gif, .jpg, .png"
            hidden
            onChange={(e) => {
              preview(e.target.files[0]);
              setFile(e.target.files[0]);
              setFileName(e.target.files[0].name);
            }}
          />
          <div className="post-attach-contents">
            {imageSrc === '' ? (
              <label className="post-attach-image" for="input-file">
                <FontAwesomeIcon icon={faPlus} size="1x" />
              </label>
            ) : (
              <img className="previewImg" src={imageSrc} />
            )}

            <div className="post-attach-count">1/10</div>
          </div>
        </div>
        <div className="post-button">
          <div className="post-button-cancel">취소</div>

          <div className="post-button-finish">
            <div
              className="post-modal-click"
              onClick={() => {
                if (!(!myContent || !starRate)) {
                  imgUpload();
                  toggleModal();
                }
                postSubmit();
              }}
            >
              작성완료
            </div>
            <PostModal />
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
                    <Link to="/mypage/footprint" style={{ color: 'inherit', textDecoration: 'none' }}>
                      <FontAwesomeIcon icon={faXmark} />
                    </Link>
                  </button>
                  <img className="post-modal-image" alt="review-image" src="/image/reviewLogo.png" />
                  <div className="post-modal-notice">리뷰가 등록되었습니다.</div>
                </div>
              </StyledModal>
            </ModalProvider>
          </div>
        </div>
      </div>
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
