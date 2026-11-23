NexoraLink Starter 0.1.0
========================

यह शुरुआती Android प्रोजेक्ट है, पूरा Remote Control ऐप या तैयार APK नहीं।

अभी शामिल:
- NexoraLink का बेसिक डैशबोर्ड
- 6 अंकों का demo PIN बनाना
- 6 अंकों का PIN दर्ज करके demo request दिखाना
- स्थानीय demo consent toggle

अभी शामिल नहीं:
- वास्तविक इंटरनेट/डिवाइस कनेक्शन
- लाइव स्क्रीन शेयरिंग या टच कंट्रोल
- कैमरा, माइक्रोफोन या नोटिफिकेशन एक्सेस
- फाइल ट्रांसफर या ऐप मैनेजमेंट

Build करने के लिए:
1. ZIP को AndroidIDE/Android Studio में extract करके project खोलें।
2. Gradle sync पूरा होने दें।
3. compileSdk 35 और compatible Android Gradle Plugin उपलब्ध होना चाहिए।
4. app module का assembleDebug task चलाएँ।
5. APK सामान्यतः app/build/outputs/apk/debug/app-debug.apk में बनेगी।

Project settings:
- applicationId: com.nexoralink.app
- minSdk: 29 (Android 10)
- targetSdk: 35
- version: 0.1.0

महत्वपूर्ण:
यह starter आपके या किसी दूसरे फोन से कनेक्ट नहीं होता। वास्तविक remote-support के लिए
अलग signaling/backend, WebRTC, MediaProjection consent flow, foreground service,
secure authentication, device compatibility testing और Play policy review की जरूरत होगी।
