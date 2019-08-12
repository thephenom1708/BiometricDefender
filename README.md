# Biometric Defender
- A **Smart India Hackathon 2019 (Software Edition) Winning** Project
- It is a secure digital system for encryption of biometric traits. The REST API has been built using python on Django framework. All the computation and processing is built using Java. It is a distributed decentralised system where we store the thumb impression templates on distributed servers by applying distortion algorithm and carry out authentication process.

## Methodology
* Using biometrics in authentication systems is undisputed and the most promising
technique. But it has one major disadvantage that once a biometric data of any person is
compromised, unlike passwords it cannot be changed again.
* This system solves this major issue by never storing the original biometric data of any
person on any database. The biometric data is distorted upto
some extent and then again transformed into another form by binding it with a unique key
which will result into our final biometric template which is stored into the database.
* This system is implemented for authenticating the **Fingerprint** Biometric data.

## System Architecture
* It is a **Decentralised System**. It will consist of 4 components -
  1. Computational Server (CS) 
  2. Template Database (TDB)
  3. Key Database (KDB)
  4. Authentication Server (AS).
* CS will convert the original biometric into template (distortion + key binding) and then
storing it to TDB during registration. KDB will be on cloud which will store the key of
users. CS will also convert the fresh biometric into the template form which will be
compared with the template of that person stored in TDB. This comparison will be done
by the AS and AS will give a binary result i.e. Authentication Successful or Failed.
* The system architecture as stated will be distributed. That is the attacker will need to
gain access of all 4 entities i.e. CS, TDB, KDB and AS. And even if such a worst case
happens, system is not storing the original biometric of a person and can always generate a
new biometric for that person by different distortion technique.
* To summarise - the system will not store original biometric trait thus ensuring that any
person’s biometric information is never compromised and inspite of not storing the
original biometric the system will provide a foolproof and secure multi factor
authentication system.

![](https://github.com/thephenom1708/BiometricDefender/blob/master/biometrics.png)


