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

  const [starRate, setStarRate] = useState(null);
  const getStarRate = (rate) => {
    setStarRate(rate);
  };

  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  const postSubmit = () => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    axios
      .post('http://3.34.139.61:8080/api/shops/1/reviews', config, {
        content: myContent,
        tip: myTip,
        imageUrls: [],
        star: 5,
      })
      .then(function (response) {})
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <>
      {isMobile ? <div></div> : <Header />}

      <div className="post-wrapper">
        {isMobile ? <div className="post-title">리뷰쓰기</div> : <div className="post-title">리뷰쓰기</div>}

        <div className="post-star">
          <Star getStarRate={getStarRate} />
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
          <div className="post-button-finish" onClick={postSubmit}>
            <PostModal />
          </div>
        </div>
      </div>
    </>
  );
}
