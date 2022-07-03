import React, { useEffect, useState } from 'react';
import Slider from 'react-slick';
import '../../../styles/slick.css';
import '../../../styles/slick-theme.css';
import '../../../styles/detail.css';
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

function SampleNextArrow_mobile(props) {
  const { className, style, onClick } = props;
  return (
    <div className={className} style={{ ...style, display: 'block', background: '#FFFFFF96', borderRadius: '20px', paddingLeft: '18px', paddingTop: '13px' }} onClick={onClick} />
  );
}

function SamplePrevArrow_mobile(props) {
  const { className, style, onClick } = props;
  return (
    <div className={className} style={{ ...style, display: 'block', background: '#FFFFFF96', borderRadius: '20px', paddingLeft: '16px', paddingTop: '13px' }} onClick={onClick} />
  );
}

export default function Detail({ isMobile, imageInfo }) {
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
  const settings_mobile = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    centerMode: false,
    nextArrow: <SampleNextArrow_mobile />,
    prevArrow: <SamplePrevArrow_mobile />,
  };

  return (
    <>
      {isMobile === '1' ? (
        <Slider {...settings_mobile} className="detail-slider">
          <div className="detail-image-wrapper">
            <img className="detail-main-image" alt="review-image" src="/image/detail.png" />
          </div>
          <div className="detail-sub-wrapper">
            <div className="detail-sub-first-column-wrapper">
              <div className="detail-sub-first-image-wrapper">
                <img className="detail-sub-image" alt="review-image" src="/image/detail.png" />
              </div>
              <div className="detail-sub-image-wrapper">
                <img className="detail-sub-image" alt="review-image" src="/image/detail.png" />
              </div>
            </div>
            <div className="detail-sub-column-wrapper">
              <div className="detail-sub-first-image-wrapper">
                <img className="detail-sub-image" alt="review-image" src="/image/detail.png" />
              </div>
              <div className="detail-sub-image-wrapper">
                <img className="detail-sub-image" alt="review-image" src="/image/detail.png" />
              </div>
            </div>
          </div>
          <div className="detail-image-wrapper">{imageInfo ? <img className="detail-main-image" alt="review-image" src={imageInfo} /> : ''}</div>
          <div className="detail-sub-wrapper">
            <div className="detail-sub-column-wrapper">
              <div className="detail-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
              <div className="detail-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
            </div>
            <div className="detail-sub-column-wrapper">
              <div className="detail-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
              <div className="detail-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
            </div>
          </div>
        </Slider>
      ) : (
        <Slider {...settings} className="detail-slider">
          <div className="detail-image-wrapper">
            <img className="detail-main-image" alt="review-image" src="/image/detail.png" />
          </div>
          <div className="detail-sub-wrapper">
            <div className="detail-sub-column-wrapper">
              <div className="detail-sub-image-wrapper">
                <img className="detail-sub-image" alt="review-image" src="/image/detail.png" />
              </div>
              <div className="detail-sub-image-wrapper">
                <img className="detail-sub-image" alt="review-image" src="/image/detail.png" />
              </div>
            </div>
            <div className="detail-sub-column-wrapper">
              <div className="detail-sub-image-wrapper">
                <img className="detail-sub-image" alt="review-image" src="/image/detail.png" />
              </div>
              <div className="detail-sub-image-wrapper">
                <img className="detail-sub-image" alt="review-image" src="/image/detail.png" />
              </div>
            </div>
          </div>
          <div className="detail-image-wrapper">{imageInfo ? <img className="detail-main-image" alt="review-image" src={imageInfo} /> : ''}</div>
          <div className="detail-sub-wrapper">
            <div className="detail-sub-column-wrapper">
              <div className="detail-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
              <div className="detail-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
            </div>
            <div className="detail-sub-column-wrapper">
              <div className="detail-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
              <div className="detail-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
            </div>
          </div>
        </Slider>
      )}
    </>
  );
}
