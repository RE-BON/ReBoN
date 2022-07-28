import '../../../styles/header-search-bar.css';
// import React, { useState } from 'react';

export default function Searchbar() {
  return (
    <div className="search-bar-wrapper">
      <input placeholder="가고 싶은 지역을 입력해주세요." />
      <img src="../../../../image/search-icon.png" alt="header-search-icon" />
    </div>
  );
}
