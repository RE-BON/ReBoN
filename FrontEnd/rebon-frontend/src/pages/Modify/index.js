import '../../styles/post.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { useMediaQuery } from 'react-responsive';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import Header from '../../components/Header';
import Star from './Star';
import ModifyModal from './ModifyModal';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AWS from 'aws-sdk';

export default function Modify() {
  const [review, setReview] = useState({});
  useEffect(() => {
    axios
      .get('http://3.34.139.61:8080/api/my-reviews')
      .then((response) => {
        console.log(1111111111111111);
        // setReview(response.data);
        // console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const [imageSrc, setImageSrc] = useState('');
  const region = 'us-east-1';
  const bucket = 'elice-boardgame-project';

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

  return (
    <>
      {isMobile ? <div></div> : <Header />}

      <div className="post-wrapper">
        {isMobile ? (
          <div className="post-title">
            <FontAwesomeIcon icon={faArrowLeft} />
            리뷰쓰기
          </div>
        ) : (
          <div className="post-title">리뷰쓰기</div>
        )}

        <div className="post-star">
          <Star />
        </div>

        {isMobile ? (
          <div className="post-tip-mobile">
            <div className="post-tip-box-mobile">나만의 꿀팁</div>
            <input
              placeholder="마라도 횟집에 대한 나만의 꿀팁을 적어주세요&#13;&#10;ex.2층 창가자리 뷰가 예뻐요)"
              maxLength="500"
            />
          </div>
        ) : (
          <div className="post-tip">
            <div className="post-tip-box">나만의 꿀팁</div>
            <input placeholder="''마라도 횟집''에 대한 나만의 꿀팁을 적어주세요(ex.2층 창가자리 뷰가 예뻐요)" maxLength="500" />
          </div>
        )}

        <div className="post-tip-count">1/500</div>

        <div className="post-review">
          <textarea rows="6" placeholder="  ''마라도 횟집''에 대한 리뷰를 적어주세요" maxLength="500"></textarea>
          <div className="post-review-count">1/1000</div>
        </div>

        <div className="post-attach">
          <input
            type="file"
            id="input-file"
            accept=".gif, .jpg, .png"
            hidden
            onChange={(e) => {
              preview(e.target.files[0]);
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
            <ModifyModal />
          </div>
        </div>
      </div>
    </>
  );
}
