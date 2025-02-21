SUMMARY = "U-Boot boot script generator"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DEPENDS = "u-boot-mkimage-native"

SRC_URI = "file://boot.cmd"

do_compile() {
	mkimage -C none -A arm -T script -d "${WORKDIR}/boot.cmd" boot.scr
}

inherit deploy

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 boot.scr ${DEPLOYDIR}
}

addtask do_deploy after do_compile before do_build

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(olimex-imx8mp-evb)"
