# Biometric Defender
A Smart India Hackathon 2019 (Software Edition) Winning Project

## Methodology
* Using biometrics in authentication systems is undisputed and the most promising
technique. But it has one major disadvantage that once a biometric data of any person is
compromised, unlike passwords it cannot be changed again.
• We are solving this major issue by never storing the original biometric data of any
person on any database. We will be storing biometric data by intentionally distorting it to
some extent and then again transforming into another form by binding it with a unique key
which will result into our final biometric template which will be stored into the database.
• Advantage of distortion is that even if the biometric data is stolen a new biometric will be
generated for the same person just by changing the arguments for the distortion
function. Advantage of using a unique key is that this key will not be known even by the
user itself nor the Computational Server (explained further).
• Fingerprint is the most used and reliable form of biometric so we will be using it for our
system. Same approach can be used for other type of biometric data.


