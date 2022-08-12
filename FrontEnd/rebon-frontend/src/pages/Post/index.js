import '../../styles/post.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { useMediaQuery } from 'react-responsive';
import Header from '../../components/Header';
import Star from './Star';
import PostModal from './PostModal';
import { useState } from 'react';
import axios from 'axios';
import AWS from 'aws-sdk';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { Link } from 'react-router-dom';
import { faXmark } from '@fortawesome/free-solid-svg-icons';

export default function Post() {
  const [myTip, setMyTip] = useState('');
  const onChangeMyTip = (e) => {
    setMyTip(e.target.value);
  };

  const [myContent, setMyContent] = useState('');
  const onChangeMyContent = (e) => {
    setMyContent(e.target.value);
  };
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
        setOpacity(0);
        setIsOpen(!isOpen);
      },
      function (err) {
        // 이미지 업로드 실패
        alert('이미지 업로드 실패');
      }
    );
  };

  const postSubmit = () => {
    axios
      .post('http://3.34.139.61:8080/api/shops/1/reviews', {
        content: '맛이 좋아요',
        tip: '필수로 시키자',
        imageUrls: [],
        star: 5,
      })
      .then(function (response) {
        // -- 이 200일 경우
        console.log(111111111111111);
      })
      .catch(function (error) {
        // 오류발생시 실행 -- 이 400일 경우, alert error 출력, 닉네임 input 공백,
        console.log(error);
      })
      .then(function () {
        // 항상 실행
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
        {isMobile ? <div className="post-title">리뷰쓰기</div> : <div className="post-title">리뷰쓰기</div>}

        <div className="post-star">
          <Star />
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
            <div className="post-modal-click" onClick={imgUpload}>
              작성완료
            </div>
            {/* <PostModal /> */}
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
                  <img className="post-modal-image" alt="review-image" src="image/reviewLogo.png" />
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
