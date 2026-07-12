$(document).ready(function() {
  
  // 1. Your array of raw image data (can come from an API or DOM grab)
  var imagesData = [
    { src: 'https://picsum.photos/id/10/1200/600', alt: 'First Slide', caption: 'Beautiful Mountains' },
    { src: 'https://picsum.photos/id/20/1200/600', alt: 'Second Slide', caption: 'Deep Forest Woods' },
    { src: 'https://picsum.photos/id/30/1200/600', alt: 'Third Slide', caption: 'Sunny Coastline' }
  ];

  // 2. Define a target container on your page where you want to inject it
  // For demonstration, we will append it straight to the body or an existing wrapper
  var $targetContainer = $('#carousel-wrapper').length ? $('#carousel-wrapper') : $('body');

  // 3. Create the Main Bootstrap 5 Carousel Container Shell
  var carouselId = 'dynamicBootstrap5Carousel';
  var $carouselContainer = $('<div />', {
    'id': carouselId,
    'class': 'carousel slide carousel-fade', // Adding 'carousel-fade' for smooth crossfades
    'data-bs-ride': 'carousel'
  });

  // 4. Create the core skeletal elements
  var $indicatorsContainer = $('<div class="carousel-indicators"></div>');
  var $innerContainer = $('<div class="carousel-inner"></div>');

  // 5. Loop through images array and construct the items concurrently
  $.each(imagesData, function(index, item) {
    // Generate indicators
    var $indicatorBtn = $('<button />', {
      'type': 'button',
      'data-bs-target': '#' + carouselId,
      'data-bs-slide-to': index,
      'aria-label': 'Slide ' + (index + 1)
    });

    // Generate slides
    var $carouselItem = $('<div class="carousel-item"></div>');
    var $img = $('<img />', {
      'src': item.src,
      'class': 'd-block w-100', // Bootstrap sizing classes
      'alt': item.alt
    }).css({
      'max-height': '600px',     // Standardize sizing height constraints
      'object-fit': 'cover'
    });

    // Optional text overlays
    var $caption = $('<div class="carousel-caption d-none d-md-block"></div>')
      .append('<h5>' + item.alt + '</h5>')
      .append('<p>' + item.caption + '</p>');

    // Mark the very first element active so the layout displays initially
    if (index === 0) {
      $indicatorBtn.addClass('active').attr('aria-current', 'true');
      $carouselItem.addClass('active');
    }

    // Append to parents
    $indicatorsContainer.append($indicatorBtn);
    $carouselItem.append($img).append($caption);
    $innerContainer.append($carouselItem);
  });

  // 6. Create Navigation Controls (Prev/Next Arrows)
  var $prevControl = $(
    '<button class="carousel-control-prev" type="button" data-bs-target="#' + carouselId + '" data-bs-slide="prev">' +
      '<span class="carousel-control-prev-icon" aria-hidden="true"></span>' +
      '<span class="visually-hidden">Previous</span>' +
    '</button>'
  );

  var $nextControl = $(
    '<button class="carousel-control-next" type="button" data-bs-target="#' + carouselId + '" data-bs-slide="next">' +
      '<span class="carousel-control-next-icon" aria-hidden="true"></span>' +
      '<span class="visually-hidden">Next</span>' +
    '</button>'
  );

  // 7. Assemble all parts into the root element, then push into the live DOM
  $carouselContainer
    .append($indicatorsContainer)
    .append($innerContainer)
    .append($prevControl)
    .append($nextControl);

  $targetContainer.append($carouselContainer);

  // 8. CRITICAL BOOTSTRAP 5 STEP: Initialize via native JS engine
  // Because BS5 dropped core jQuery initialization wrappers, we reference the raw HTML node
  var carouselEl = document.getElementById(carouselId);
  var bootstrapCarousel = new bootstrap.Carousel(carouselEl, {
    interval: 3000, // Slide changes every 3 seconds
    wrap: true,     // Loop continuously back to slide 1
    touch: true     // Allow swipe gesture mechanics on mobile screens
  });

});