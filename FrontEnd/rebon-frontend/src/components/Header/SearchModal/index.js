import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import SearchBar from './SearchBar';
import axios from 'axios';
import '../../../styles/header-search-modal.css';

export default function SearchModal() {
  const [isOpen, setIsOpen] = useState(false);
  const [opacity, setOpacity] = useState(0);

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

  const [tag, setTags] = useState([]);
  useEffect(() => {
    axios
      .get('http://34.238.48.93:8080/api/tags')
      .then((response) => {
        setTags(response.data);
        console.log(tag[8]);
      })
      .catch((error) => {
        console.log('error');
      });
  }, []);

  const FadingBackground = styled(BaseModalBackground)`
    opacity: ${(props) => props.opacity};
    transition: all 0.3s ease-in-out;
  `;

  return (
    <ModalProvider backgroundComponent={FadingBackground}>
      <div onClick={toggleModal}>
        <SearchBar />
      </div>
      <StyledModal
        isOpen={isOpen}
        afterOpen={afterOpen}
        beforeClose={beforeClose}
        onBackgroundClick={toggleModal}
        onEscapeKeydown={toggleModal}
        opacity={opacity}
        backgroundProps={{ opacity }}
      >
        <div className="header-modal-wrapper">
          <div className="header-modal-wrapper-search">
            <div className="header-search-bar-wrapper">
              <img src="../../../../image/search-icon.png" alt="header-search-icon" />
              <input placeholder="가고 싶은 지역을 입력해주세요." />
            </div>
          </div>

          <ul className="header-search-list">
            <li>장성동</li>
            <li>잔치국수</li>
            <li>장수동</li>
          </ul>

          <ul className="header-tag-list">
            <div>추천 태그로 검색해보세요.</div>
            {tag.map((item) => (
              <Link to="/main" state={{ item }}>
                <li>{item.name}</li>
              </Link>
            ))}
          </ul>
        </div>
      </StyledModal>
    </ModalProvider>
  );
}

const StyledModal = Modal.styled`
position:absolute;
top: -1%;
  width: 100%;
  height: 24rem;
  padding : 1% 20%;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;`;
