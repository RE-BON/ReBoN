import React from 'react';
import Header from '../../components/Header';
import { Link } from 'react-router-dom';
import '../../styles/noresult.css';

export default function NoResult() {
  return (
    <>
      <Header />
      <div className="no-wrapper">
        <div className="no-icon">
          <img src="/image/no.png" />
        </div>
        <div className="no-notice">
          검색된 결과가 없습니다.
        </div>
      </div>
    </>
  );
}
