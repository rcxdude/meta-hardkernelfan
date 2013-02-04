COMPATIBLE_MACHINE = "odroid-u2"

require linux.inc

DESCRIPTION = "Linux kernel for the Hardkernel ODROID-U2 board"

PR = "r0"

# Bump MACHINE_KERNEL_PR in the machine config if you update the kernel.
SRCREV = "a6d395eeb38a823edb01dc25c8fd5c092fb22e60"

#SRC_URI = "git://github.com/hardkernel/linux.git;protocol=git;branch=odroid-3.0.y"
SRC_URI = "file:///home/jamesbuckley/odroid_kernel.git.tar.gz"
SRC_URI += "file://0001-disable-arch-extension.patch"
SRC_URI[md5sum] = "8e66622f104a851b8a01ad4b702693b0"
SRC_URI[sha256sum] = "b7f1e7bdcba1b64d1b38fb265f73d0702d1ed4a3cf06628f78c12f41fd5ebd18"

LINUX_VERSION ?= "3.0.61-odroid-u2"
PV = "${LINUX_VERSION}+${PR}+git${SRCREV}"

S = "${WORKDIR}/odroid_kernel.git"

# NOTE: For now we pull in the default Ubuntu config from the odroid kernel GIT tree.

KERNEL_DEFCONFIG = "odroidu2_ubuntu_defconfig"

# CMDLINE for odroid. No idea what to put here yet (been copied from the meta-rasberrypi layer)
CMDLINE_odroid-u2 = ""

PARALLEL_MAKEINST = ""

do_configure_prepend() {
	install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

do_install_prepend() {
	install -d ${D}/lib/firmware
}
