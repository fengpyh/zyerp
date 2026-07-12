$(document).ready(function() {
  // 1. Listen for clicks on images inside paragraphs
  $('body').on('click', 'p img', function() {
    var imgSrc = $(this).attr('src');
    var imgAlt = $(this).attr('alt') || 'Modal Image';

    // 2. Dynamically create the modal elements and basic styles
    var $modal = $('<div class="dynamic-modal"></div>').css({
        'position': 'fixed',
        'top': 0,
        'left': 0,
        'width': '100vw',
        'height': '100vh',
        'background': 'rgba(0, 0, 0, 0.8)',
        'display': 'flex',
        'justify-content': 'center', // Centers horizontally
        'align-items': 'center',     // Centers vertically
        'z-index': 10000,
        'cursor': 'pointer'
      });

    var $modalContent = $('<div class="modal-content"></div>').css({
        'position': 'relative',
        'width': '60vw',             // Exactly 60% of the window width
        'max-height': '85vh'         // Prevents image from cutting off on short, wide screens
      });

    var $modalBody = $('<div class="modal-body"></div>');
    var $modalImg = $('<img />', { src: imgSrc, alt: imgAlt }).css({
      'width': '100%',
      'height': '100%',
      'max-height': '75vh',
      'object-fit': 'fill',
      'box-shadow': '0 4px 20px rgba(0,0,0,0.5)',
      'border-radius': '4px',
      'padding': '5px'
    });

    var $closeBtn = $('<span class="close-modal">&times;</span>').css({
      'position': 'absolute',
      'top': '-40px',
      'right': '0',
      'color': '#fff',
      'font-size': '30px',
      'font-weight': 'bold',
      'cursor': 'pointer'
    });
    
    //var $modelHeaer = $('<div class="modal-header"><h5 class="modal-title" id="title_area">Image</h5><button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button></div>');
    var $modelHeaer = $('<div class="modal-header"><h5 class="modal-title" id="title_area">Image</h5></div>');
    var $closeBtn2 = $('<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>');
    $modelHeaer.append($closeBtn2)
    
    $modalBody.append($modalImg);
    
    // 3. Assemble and inject into the DOM
    //$modalContent.append($closeBtn).append($modalImg);
    $modalContent.append($modelHeaer).append($modalBody);
    $modal.append($modalContent);
    $('body').append($modal);

    // 4. Close modal behavior (removes it completely from DOM)
    $modal.on('click', function() {
      $modal.fadeOut(200, function() {
        $(this).remove();
      });
    });

    // Prevent clicking the image itself from closing the modal instantly
    $modalContent.on('click', function(e) {
      e.stopPropagation();
    });
    
    // Allow the close button specifically to close it too
    $closeBtn2.on('click', function() {
      $modal.click(); 
    });
  });
});