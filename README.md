[![Stories in Ready](https://badge.waffle.io/jughyd/gitfx.png?label=ready&title=Ready)](https://waffle.io/jughyd/gitfx)
GitFx
=====

[![Join the chat at https://gitter.im/jughyd/GitFx](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/jughyd/GitFx?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Build Status](https://travis-ci.org/jughyd/GitFx.svg?branch=master)](https://travis-ci.org/jughyd/GitFx)

Gitfx is a Git client built using JavaFX


#Building GitFx
Prerequisiste 
<a href="https://jdk8.java.net/download.html">Download JDK 8u60 Build b07</a> pre release to run the application.

1. GitFx uses maven build system. 
2. Fork GitFx and clone your fork
3. Clone using ```$git clone [URL].git```
4. Change directory to your clone location ```cd GitFx``` 
5. Build using ```mvn clean install``` 

##Using NetBeans IDE
1. File -> Open Project 
2. Select the Projects cloned folder GitFx<br/><img src='http://s14.postimg.org/z9dxp8yc1/Select_Git_Fx_Netbeans.png' border='0' alt="Select Git Fx Netbeans" /> 
3. Clean Build the Project and Run</br><img src='http://s27.postimg.org/fq7gssgbn/Build_Netbeans.png' border='0' alt="Build Netbeans" />
4. [Optional Step]You can skip tests while building. Right click on Project and select Properties. Click on Actions. Set the properties Skip Tests. </br><img src='http://s1.postimg.org/o73gcp3ov/Skiptests_Netbeans.png' border='0' alt="Skiptests Netbeans" />

Note: IntelliJ and Eclipse can be used to build GitFx. However SceneBuilder works well with NetBeans to build JavaFX pages. 

#License
GitFx source is available under <a href="https://www.apache.org/licenses/LICENSE-2.0">Apache License Version 2.0 </a> 
