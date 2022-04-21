import React, { useState } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import '../../../../styles/footprint-modal.css';

export default function FootprintModal() {
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

  const StyledModal = Modal.styled`
  width: 23rem;
  height: 10rem;
  padding : 20px;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;
  `;

  const FadingBackground = styled(BaseModalBackground)`
    opacity: ${(props) => props.opacity};
    transition: all 0.3s ease-in-out;
  `;

  return (
    <div className="footprint-modal-wrapper">
      <ModalProvider backgroundComponent={FadingBackground}>
        <div className="footprint-modal" onClick={toggleModal}>
          리뷰삭제
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
          <div className="footprint-modal-content">
            <div className="footprint-modal-delete">
              <button onClick={toggleModal}>
                <img src="../../../../image/trash.png" alt="trash-img" />
              </button>
              삭제하기
            </div>
            <div className="footprint-modal-close">
              <button onClick={toggleModal}>
                <FontAwesomeIcon icon={faXmark} />
              </button>
              닫기
            </div>
          </div>
        </StyledModal>
      </ModalProvider>
    </div>
  );
}
