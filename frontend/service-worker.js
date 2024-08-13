const CACHE_NAME = 'license-generator-cache-v1';
const urlsToCache = [
    '/',
    '/index.html',
    '/styles.css',
    '/app.js',
    '/https://cdn.jsdelivr.net/npm/pdf417-js@1.1.0/pdf417.min.js'
];

self.addEventListener('install', event => {
    event.waitUntil(
        caches.open(CACHE_NAME)
        .then(cache => {
            console.log('Opened cache');
            return cache.addAll(urlsToCache);
       **`service-worker.js` (continued)**

```javascript
        })
    );
});

self.addEventListener('fetch', event => {
    event.respondWith(
        caches.match(event.request)
        .then(response => {
            // Cache hit - return response from cache
            if (response) {
                return response;
            }
            return fetch(event.request);
        })
    );
});
<!-- // 💡✌🏾 In the End, He made them Open-Source 💜❤️ // -->
